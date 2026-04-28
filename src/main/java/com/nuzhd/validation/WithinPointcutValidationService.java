package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.WITHIN;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class WithinPointcutValidationService extends PointcutValidationService {

    public WithinPointcutValidationService(MessageSource messageSource) {
        super(messageSource, WITHIN, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
