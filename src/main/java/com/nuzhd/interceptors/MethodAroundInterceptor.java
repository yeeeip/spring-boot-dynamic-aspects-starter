package com.nuzhd.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Locale;

import static com.nuzhd.messages.DynamicAspectsMessageKeys.METHOD_CALLED_KEY;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.METHOD_EXECUTED_KEY;

public class MethodAroundInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodAroundInterceptor.class);

    private final MessageSource messageSource;

    public MethodAroundInterceptor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Nullable
    @Override
    public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        // Before execution
        var fullMethodName = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName() + "()";
        LOGGER.info(messageSource.getMessage(METHOD_CALLED_KEY, new Object[] {fullMethodName}, Locale.ROOT));
        if (invocation.getArguments().length != 0) {
            LOGGER.info(Arrays.toString(invocation.getArguments()));
        }

        var retVal = invocation.proceed();

        // After execution
        LOGGER.info(messageSource.getMessage(METHOD_EXECUTED_KEY, new Object[] {fullMethodName}, Locale.ROOT));
        if (!invocation.getMethod().getReturnType().equals(Void.TYPE)) {
            LOGGER.info("{}", retVal);
        }

        return retVal;
    }
}
