package com.taf.sample.framework.utils;

import org.apache.commons.lang3.StringUtils;

public class Helper {
    /**
     * Creates a string of integer value. Adds zeros to make it of appropriate length if value is not that long.
     *
     * @param length length of the string.
     * @param value  string value(Integer type).
     * @return string of needed length.
     */
    public static String generateStringWithFixedLengthPadWithZero(int length, int value) {
        return StringUtils.leftPad(String.valueOf(value), length, '0');
    }

    /**
     * Creates a string of long value. Adds zeros to make it of appropriate length if value is not that long.
     *
     * @param length length of the string.
     * @param value  string value(Integer type).
     * @return string of needed length.
     */
    public static String generateStringWithFixedLengthPadWithZero(int length, long value) {
        return StringUtils.leftPad(String.valueOf(value), length, '0');
    }

    /**
     * Generates a string with fixed length of string value.
     * Adds spaces to the left to make it of appropriate length if value is not tha long.
     *
     * @param length length of the string.
     * @param value  string value(String type).
     * @return string of needed length.
     */
    public static String generateStringWithFixedLengthPadWithSpace(int length, String value) {
        return StringUtils.leftPad(value, length, ' ');
    }

    /**
     * Generates a string with fixed length of string value.
     * Adds spaces to the right to make it of appropriate length if value is not tha long.
     *
     * @param length length of the string.
     * @param value  string value(String type).
     * @return string of needed length.
     */
    public static String fixedLengthRightPad(int length, String value) {
        return StringUtils.rightPad(value, length, ' ');
    }

    /**
     * Generates a string with fixed length of string value.
     * Adds zeros to the right to make it of appropriate length if value is not tha long.
     *
     * @param length length of the string.
     * @param value  string value(String type).
     * @return string of needed length.
     */
    public static String fixedLengthRightPadWithZero(int length, String value) {
        return StringUtils.rightPad(value, length, '0');
    }
}
