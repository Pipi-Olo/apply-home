package com.pipiolo.home.job;

import com.pipiolo.home.adapter.HomeApiResource;
import com.pipiolo.home.dto.HomeDto;
import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.service.EmailService;
import com.pipiolo.home.service.HomeRequestService;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class HomeInsertJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final HomeApiResource homeApiResource;

    @Bean
    public Job homeInsertJob(Step homeInsertStep) {
        return jobBuilderFactory.get("homeInsertJob")
                .incrementer(new RunIdIncrementer())
                .start(homeInsertStep)
                .build();
    }

    @JobScope
    @Bean
    public Step homeInsertStep(
            StaxEventItemReader<HomeDto> homeItemReader,
            ItemProcessor<HomeDto, HomeRequest> homeItemProcessor,
            ItemWriter<HomeRequest> homeItemWriter
    ) {
        return stepBuilderFactory.get("homeInsertStep")
                .<HomeDto, HomeRequest>chunk(10)
                .reader(homeItemReader)
                .processor(homeItemProcessor)
                .writer(homeItemWriter)
                .build();
    }

    @StepScope
    @Bean
    public StaxEventItemReader<HomeDto> homeItemReader(
            @Value("#{jobParameters['startMonth']}") String startMonth,
            @Value("#{jobParameters['endMonth']}") String endMonth,
            Jaxb2Marshaller jaxb2Marshaller
    ) {
        return new StaxEventItemReaderBuilder<HomeDto>()
                .name("homeItemReader")
                .resource(homeApiResource.getResource(startMonth, endMonth))
//                .resource(new ClassPathResource(filePath))
                .addFragmentRootElements("item")
                .unmarshaller(jaxb2Marshaller)
                .build();
    }

    @StepScope
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(HomeDto.class);
        return jaxb2Marshaller;
    }

    @StepScope
    @Bean
    public ItemProcessor<HomeDto, HomeRequest> homeItemProcessor(
            HomeRequestService service
    ) {
        return new ItemProcessor<HomeDto, HomeRequest>() {
            @Override
            public HomeRequest process(HomeDto item) throws Exception {
                return service.from(item);
            }
        };
    }

    @StepScope
    @Bean
    public ItemProcessorAdapter<HomeDto, HomeRequest> homeItemProcessorAdapter(
            HomeRequestService service
    ) {
        ItemProcessorAdapter<HomeDto, HomeRequest> adapter = new ItemProcessorAdapter<>();
        adapter.setTargetObject(service);
        adapter.setTargetMethod("from");
        return adapter;
    }

    @StepScope
    @Bean
    public ItemWriter<HomeRequest> homeItemWriter(
            HomeService homeService,
            EmailService emailService
    ) {
        return items -> {
            items.forEach(homeService::upsert);
            items.forEach(emailService::send);
//            items.forEach(System.out::println);
//            System.out.println("============= Writing Completed =============");
        };
    }
}
