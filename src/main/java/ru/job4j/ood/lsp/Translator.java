package ru.job4j.ood.lsp;

public class Translator {
    protected String text;

    public String translate(String text) {
        String rsl = text.toUpperCase();
        if (text.startsWith("Ы")) {
            throw new IllegalArgumentException("This is not appropriate beginning of the text");
        }
        return rsl;
    }
}
