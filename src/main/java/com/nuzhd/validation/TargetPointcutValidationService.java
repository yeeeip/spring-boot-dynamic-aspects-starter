package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.TARGET;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class TargetPointcutValidationService extends PointcutValidationService {

    public TargetPointcutValidationService(MessageSource messageSource) {
        super(messageSource, TARGET, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
