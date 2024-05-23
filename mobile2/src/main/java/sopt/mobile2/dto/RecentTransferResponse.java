package sopt.mobile2.dto;

import java.time.LocalDateTime;


public record RecentTransferResponse(
        String accountName,
        long accountNumber,
        boolean isAccountLike,
        LocalDateTime createdAt,
        String bankName,
        String imgUrl,
        Long accountId
) {

//    public String getAccountName() {
//        return accountName;
//    }
//
//    public int getAccountNumber() {
//        return accountNumber;
//    }
//
//    public boolean isAccountLike() {
//        return accountLike;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//    public String getBankName(){
//        return bankName;
//    }
//    public String getImgUrl(){
//        return imgUrl;
//    }
}
