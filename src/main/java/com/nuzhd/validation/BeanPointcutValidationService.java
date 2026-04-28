package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.BEAN;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class BeanPointcutValidationService extends PointcutValidationService {

    public BeanPointcutValidationService(MessageSource messageSource) {
        super(messageSource, BEAN, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
