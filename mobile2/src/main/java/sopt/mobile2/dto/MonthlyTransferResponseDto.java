package sopt.mobile2.dto;

public record MonthlyTransferResponseDto(String accountName, String date,
                                         long transferAmount, long balance, boolean isWithdraw, String hashTag) {
}
