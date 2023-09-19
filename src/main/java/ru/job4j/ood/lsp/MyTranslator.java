package ru.job4j.ood.lsp;

public class MyTranslator extends Translator {
    @Override
    public String translate(String text) {
        return text.toUpperCase();
    }
}
