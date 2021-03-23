package org.levelup.threads.sync;

import lombok.AllArgsConstructor;

public class TransferMoney {
    public static void main(String[] args) {

        Account acc1 = new Account(1034, 545.23);
        Account acc2 = new Account(15465, 39574.45);
        Account acc3 = new Account(6534, 6545.34);

        Thread t1 = new Thread(() -> withdraw(acc1, acc2, 43.43));
    }
    @AllArgsConstructor
    static class Account {
        int accountNumber;
        double value;
    }
    static void withdraw(Account f, Account s, double amount) {
        Account first = f.accountNumber > s.accountNumber ? f : s;
        Account second = first == f ? s : f;
        synchronized (first) {
            synchronized (second) {
                f.value -= amount;
                s.value += amount;
            }
        }
    }
}
