package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.AT_ARGS;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class AtArgsPointcutValidationService extends PointcutValidationService {

    public AtArgsPointcutValidationService(MessageSource messageSource) {
        super(messageSource, AT_ARGS, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
