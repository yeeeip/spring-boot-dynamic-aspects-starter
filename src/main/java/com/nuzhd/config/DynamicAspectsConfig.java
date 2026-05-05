package com.nuzhd.config;

import com.nuzhd.creators.DynamicAspectsCreator;
import com.nuzhd.domain.DesignatorType;
import com.nuzhd.interceptors.MethodAroundInterceptor;
import com.nuzhd.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.nuzhd.messages.DynamicAspectsMessageKeys.CREATE_ASPECTS_START_KEY;

@Configuration
@ConditionalOnProperty(name = "app.dynamic-aspects.enabled", havingValue = "true")
public class DynamicAspectsConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicAspectsConfig.class);

    @Bean
    public DynamicAspectsCreator customAdvisorsConfiguration(
            ConfigurableEnvironment environment,
            Map<DesignatorType, PointcutValidationService> validators,
            MessageSource messageSource) {
        LOGGER.info(messageSource.getMessage(CREATE_ASPECTS_START_KEY, null, Locale.ROOT));
        return new DynamicAspectsCreator(environment, methodAroundInterceptor(messageSource), messageSource, validators);
    }

    @Bean
    public MethodAroundInterceptor methodAroundInterceptor(
            MessageSource messageSource) {
        return new MethodAroundInterceptor(messageSource);
    }

    @Bean(name = "validators")
    public Map<DesignatorType, PointcutValidationService> pointcutValidators(List<PointcutValidationService> services) {
        return services.stream()
                       .collect(Collectors.toUnmodifiableMap(
                               PointcutValidationService::getDesignatorType,
                               Function.identity()
                       ));
    }

    @Bean
    public MessageSource dynamicAspectsMessageSource() {

        var messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("messages/dynamic_aspects_message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(false);

        return messageSource;
    }
}
