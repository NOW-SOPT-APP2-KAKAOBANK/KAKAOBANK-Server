package sopt.mobile2.dto;

import sopt.mobile2.domain.Account;

public record AccountInfoResponseDto(String accountName, int balance, int accountNumber) {

    public static AccountInfoResponseDto of(Account account) {
        return new AccountInfoResponseDto(account.getAccountName(), account.getBalance(), account.getAccountNumber());
    }
}
