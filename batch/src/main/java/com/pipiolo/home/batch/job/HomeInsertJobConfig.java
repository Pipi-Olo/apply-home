package com.pipiolo.home.batch.job;

import com.pipiolo.home.batch.dto.HomeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class HomeInsertJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

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
            ItemWriter<HomeDto> itemWriter
    ) {
        return stepBuilderFactory.get("homeInsertStep")
                .<HomeDto, HomeDto>chunk(10)
                .reader(homeItemReader)
                .writer(itemWriter)
                .build();
    }

    @StepScope
    @Bean
    public StaxEventItemReader<HomeDto> homeItemReader(
            @Value("#{jobParameters['filePath']}") String filePath,
            Jaxb2Marshaller jaxb2Marshaller
    ) {
        return new StaxEventItemReaderBuilder<HomeDto>()
                .name("homeItemReader")
                .resource(new ClassPathResource(filePath))
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
    public ItemWriter<HomeDto> itemWriter() {
        return items -> {
            items.forEach(System.out::println);
            System.out.println("============= Writing Completed =============");
        };
    }
}
