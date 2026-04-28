package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.AT_TARGET;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class AtTargetPointcutValidationService extends PointcutValidationService {

    public AtTargetPointcutValidationService(MessageSource messageSource) {
        super(messageSource, AT_TARGET, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
