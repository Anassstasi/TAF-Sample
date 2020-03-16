package com.taf.sample.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    /**
     * Gets a string by its regular expression.
     *
     * @param regex regular expression.
     */
    public static String getStringByPattern(String stringLine, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringLine);
        matcher.find();
        return matcher.group(0);
    }

    /**
     * Checks whether pattern is present in the string..
     *
     * @param regex regular expression.
     */
    public static boolean isPatternPresentInString(String stringLine, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringLine);
        return matcher.matches();
    }

    /**
     * Gts number of regular expression matches in the string.
     *
     * @param regex regular expression.
     */
    public static int getNumberMatches(String stringLine, String regex) {
        int count = 0;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringLine);

        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
