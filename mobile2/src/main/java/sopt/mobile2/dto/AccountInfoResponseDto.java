package sopt.mobile2.dto;

import sopt.mobile2.domain.Account;

public record AccountInfoResponseDto(String accountName, long balance, long accountNumber) {

    public static AccountInfoResponseDto of(Account account) {
        return new AccountInfoResponseDto(account.getAccountName(), account.getBalance(), account.getAccountNumber());
    }
}
