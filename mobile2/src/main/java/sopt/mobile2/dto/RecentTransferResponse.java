package sopt.mobile2.dto;

import java.time.LocalDateTime;


public record RecentTransferResponse(
        String accountName,
        int accountNumber,
        boolean accountLike,
        LocalDateTime createdAt
) {

    public String getAccountName() {
        return accountName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean isAccountLike() {
        return accountLike;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
