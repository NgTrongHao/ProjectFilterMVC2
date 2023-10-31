/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Random;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class RandomStringUtil {

    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String ALPHA_UPPER_CASE = ALPHA.toUpperCase(); // A-Z
    private static final String DIGITS = "0123456789"; // 0-9
    private static final String SPECIALS = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALPHA_NUMERIC = ALPHA + ALPHA_UPPER_CASE + DIGITS;
    private static final String ALL = ALPHA + ALPHA_UPPER_CASE + DIGITS + SPECIALS;

    private static final Random generator = new Random();

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public static String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
}
