package com.taf.sample.framework.utils.generators;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.stream.Collectors;

public class RandomGenerator {

    /**
     * Generates a random string.
     *
     * @param length       length of the string.
     * @param onlyIntegers true if string should consist only integers, false - only chars.
     * @return random string.
     */
    public static String generateRandomString(int length, boolean onlyIntegers) {
        String chars;
        if (onlyIntegers) {
            chars = "0123456789";
        } else {
            chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "abcdefghijklmnopqrstuvwxyz";
        }
        return new Random().ints(length, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
    }

    /**
     * Generated a random int number within startInclusive and endExclusive.
     *
     * @param startInclusive start number.
     * @param endExclusive   end number.
     * @return random int number.
     */
    public static int nextInt(int startInclusive, int endExclusive) {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    }
}
