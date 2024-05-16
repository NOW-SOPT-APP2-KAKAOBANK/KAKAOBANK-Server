package sopt.mobile2.dto;

import sopt.mobile2.domain.Transfer;

public record MonthlyTransferResponseDto(String accountName, String date,
                                         int transferAmount, int balance) {
}
