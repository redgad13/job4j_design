package ru.job4j.ood.lsp;

import java.util.List;

public class BankAccount {
    protected long account;
    protected String userName;
    protected int balance;
    List<Long> allAccounts;

    public void setAccount(long account) {
        if (allAccounts.contains(account)) {
            this.account = account;
        }
    }
}
