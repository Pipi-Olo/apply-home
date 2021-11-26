package com.pipiolo.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver
//            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(Boolean.TRUE);
//                thymeleaf3Properties.isDecoupledLogic());

        return defaultTemplateResolver;
    }

//    @Getter
//    @RequiredArgsConstructor
//    @ConstructorBinding
//    @ConfigurationProperties("spring.thymeleaf3")
//    public static class Thymeleaf3Properties {
//        /**
//         * Thymeleaf 3 Decoupled Logic 활성화
//         */
//        private final boolean decoupledLogic;
//    }
}
