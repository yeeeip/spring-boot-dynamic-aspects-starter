package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.AT_WITHIN;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class AtWithinPointcutValidationService extends PointcutValidationService {

    public AtWithinPointcutValidationService(MessageSource messageSource) {
        super(messageSource, AT_WITHIN, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
