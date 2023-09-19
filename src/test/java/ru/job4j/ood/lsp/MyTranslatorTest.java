package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

class MyTranslatorTest {

    @Test
    void translate() {
        Translator tr = new Translator();
        Translator mtr = new MyTranslator();
        System.out.println(tr.translate("Something"));
        System.out.println(mtr.translate("Ыыыыы"));
    }
}