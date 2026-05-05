package com.nuzhd.validation;

import com.nuzhd.CustomPointcutExpression;
import com.nuzhd.config.DynamicAspectsConfig;
import com.nuzhd.domain.DesignatorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import static com.nuzhd.domain.DesignatorType.ARGS;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_ARGS_EXPRESSION_KEY;
import static com.nuzhd.messages.DynamicAspectsMessageKeys.INVALID_POINTCUT_EXPRESSION_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicPointcutValidationServiceTest {

    private CustomPointcutExpression pointcut;
    private DynamicAspectsConfig config;

    @BeforeEach
    void setUp() {
        config = new DynamicAspectsConfig();
        var messageSource = config.dynamicAspectsMessageSource();
        Map<DesignatorType, PointcutValidationService> validators = Map.of(
                DesignatorType.WITHIN, new WithinPointcutValidationService(messageSource),
                DesignatorType.THIS, new ThisPointcutValidationService(messageSource),
                DesignatorType.TARGET, new TargetPointcutValidationService(messageSource),
                DesignatorType.ARGS, new ArgsPointcutValidationService(messageSource),
                DesignatorType.AT_TARGET, new AtTargetPointcutValidationService(messageSource),
                DesignatorType.AT_ARGS, new AtArgsPointcutValidationService(messageSource),
                DesignatorType.AT_WITHIN, new AtWithinPointcutValidationService(messageSource),
                DesignatorType.AT_ANNOTATION, new AtAnnotationPointcutValidationService(messageSource),
                DesignatorType.BEAN, new BeanPointcutValidationService(messageSource)
        );
        pointcut = new CustomPointcutExpression(validators, messageSource);
    }

    @ParameterizedTest
    @MethodSource("provideValidExpressions")
    void setExpression_SupportedDesignator_CorrectExpression(String expression) {
        assertDoesNotThrow(() -> pointcut.setExpression(expression));
    }

    @ParameterizedTest
    @MethodSource("provideBlankExpressions")
    void setExpression_SupportedDesignator_BlankBodyThrowsError(DesignatorType designatorType, String expression) {
        var exception = assertThrows(IllegalArgumentException.class, () -> pointcut.setExpression(expression));

        var expectedMessage = config.dynamicAspectsMessageSource().getMessage(
                getInvalidExpressionKey(designatorType),
                new Object[] {designatorType.getValue()},
                Locale.ROOT
        );

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    private static Stream<Arguments> provideValidExpressions() {
        return Stream.of(
                Arguments.of("within(java.lang.String)"),
                Arguments.of("this(java.lang.CharSequence)"),
                Arguments.of("target(java.lang.CharSequence)"),
                Arguments.of("args(java.lang.String)"),
                Arguments.of("@target(java.lang.Deprecated)"),
                Arguments.of("@args(java.lang.Deprecated)"),
                Arguments.of("@within(java.lang.Deprecated)"),
                Arguments.of("@annotation(java.lang.Deprecated)"),
                Arguments.of("bean(testBean)")
        );
    }

    private static Stream<Arguments> provideBlankExpressions() {
        return Stream.of(
                Arguments.of(DesignatorType.WITHIN, "within(   )"),
                Arguments.of(DesignatorType.THIS, "this()"),
                Arguments.of(DesignatorType.TARGET, "target()"),
                Arguments.of(DesignatorType.ARGS, "args()"),
                Arguments.of(DesignatorType.AT_TARGET, "@target()"),
                Arguments.of(DesignatorType.AT_ARGS, "@args()"),
                Arguments.of(DesignatorType.AT_WITHIN, "@within()"),
                Arguments.of(DesignatorType.AT_ANNOTATION, "@annotation()"),
                Arguments.of(DesignatorType.BEAN, "bean()")
        );
    }

    private static String getInvalidExpressionKey(DesignatorType designatorType) {
        return ARGS.equals(designatorType) ? INVALID_ARGS_EXPRESSION_KEY : INVALID_POINTCUT_EXPRESSION_KEY;
    }
}
