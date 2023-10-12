package ru.job4j.ood.isp.bad.study;

public class EnglishVocabulary implements Vocabulary {
    @Override
    public String translateWord(String word) {
      return word.toLowerCase();
    }

    @Override
    public String translateStableExpression(String phrase) {
        return phrase.toLowerCase();
    }
}
