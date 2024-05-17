package sopt.mobile2.dto;

import sopt.mobile2.domain.Account;

public record AccountResponseDto(String accountName, int balance) {

    public static AccountResponseDto of(Account account) {
        return new AccountResponseDto(account.getAccountName(), account.getBalance());
    }
}
