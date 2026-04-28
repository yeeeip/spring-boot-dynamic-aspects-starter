package com.nuzhd.validation;

import org.springframework.context.MessageSource;

import static com.nuzhd.domain.DesignatorType.ARGS;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_ARGS_EXPRESSION_KEY;

public class ArgsPointcutValidationService extends PointcutValidationService {

    public ArgsPointcutValidationService(MessageSource messageSource) {
        super(messageSource, ARGS, INVALID_ARGS_EXPRESSION_KEY);
    }
}
