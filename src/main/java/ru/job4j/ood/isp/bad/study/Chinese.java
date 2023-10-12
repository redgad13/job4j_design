package ru.job4j.ood.isp.bad.study;

import java.util.Arrays;

public class Chinese implements Vocabulary {
    @Override
    public String translateWord(String word) {
        return "";
    }

    @Override
    public String translateStableExpression(String phrase) {
        return "";
    }

    public String translateHieroglyph(byte[] symbols) {
        return Arrays.toString(symbols).toUpperCase();
    }
}
