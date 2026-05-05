package com.nuzhd.utils;

import org.apache.commons.lang3.StringUtils;

import com.nuzhd.domain.DesignatorType;

import java.util.Arrays;

public class PointcutExpressionUtils {

    public static String extractClassName(String expression) {
        String[] parts = expression.replaceAll("\\(.*\\)", "").split("\\.");
        return String.join(".", Arrays.copyOfRange(parts, 0, parts.length - 1));
    }

    public static String extractMethodName(String expression) {
        String[] parts = expression.replaceAll("\\(.*\\)", "").split("\\.");
        return parts[parts.length - 1];
    }

    public static String extractExpression(String rawExpression, DesignatorType designator) {
        if (designator == DesignatorType.INVALID || StringUtils.isBlank(rawExpression)) {
            return StringUtils.EMPTY;
        }

        var trimmed = rawExpression.trim();
        var openParenIndex = trimmed.indexOf('(');
        var closeParenIndex = trimmed.lastIndexOf(')');

        if (openParenIndex < 0 || closeParenIndex <= openParenIndex) {
            return StringUtils.EMPTY;
        }

        var prefix = trimmed.substring(0, openParenIndex).trim();
        if (!StringUtils.equalsIgnoreCase(prefix, designator.getValue())) {
            return StringUtils.EMPTY;
        }

        return trimmed.substring(openParenIndex + 1, closeParenIndex);
    }

}
