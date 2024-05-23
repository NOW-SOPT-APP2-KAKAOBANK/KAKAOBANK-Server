package sopt.mobile2.dto;

import sopt.mobile2.domain.Account;

public record AccountResponseDto(String accountName, long balance, Long accountId) {

    public static AccountResponseDto of(Account account) {
        return new AccountResponseDto(account.getAccountName(), account.getBalance(), account.getId());
    }
}
