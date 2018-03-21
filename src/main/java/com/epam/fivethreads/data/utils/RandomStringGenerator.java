package com.epam.fivethreads.data.utils;

import java.util.Random;

public class RandomStringGenerator {
    private static final String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerLetters = "abcdefghijklmnopqrstuvwxyz";
    private static final String digitsLetters = "0123456789";
    public static final String allLetters = upperLetters + lowerLetters + digitsLetters;

    private Random random;
   
    public String generate(int randomStringLength) {
        char[] buf = new char[randomStringLength];
        random = new Random();
        char[] symbols = allLetters.toCharArray();
        for (int i = 0; i < randomStringLength; i++)
            buf[i] = symbols[random.nextInt(symbols.length-1)];
        return new String(buf);
    }
}
