package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.THIS;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;

public class ThisPointcutValidationService extends PointcutValidationService {

    public ThisPointcutValidationService(MessageSource messageSource) {
        super(messageSource, THIS, INVALID_POINTCUT_EXPRESSION_KEY);
    }
}
