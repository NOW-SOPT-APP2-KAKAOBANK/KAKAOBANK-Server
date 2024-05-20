package sopt.mobile2.dto;

public record MonthlyTransferResponseDto(String accountName, String date,
                                         int transferAmount, int balance, int month) {
}
