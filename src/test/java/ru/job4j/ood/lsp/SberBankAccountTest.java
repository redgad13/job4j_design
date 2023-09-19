package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SberBankAccountTest {

    @Test
    void setAccount() {
        BankAccount ba = new SberBankAccount();
        List<Long> accs = new ArrayList<>();
        accs.add(1111L);
        accs.add(11112L);
        accs.add(11112L);
        ba.setAccount(1111);
    }
}