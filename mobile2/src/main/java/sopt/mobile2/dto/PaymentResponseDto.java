package sopt.mobile2.dto;

import java.util.List;

public record PaymentResponseDto(int payment, boolean isTotalOverZero, List<MonthlyTransferResponseDto> monthlyTransferList) {

    public static PaymentResponseDto of(int payment, boolean isTotalOverZero, List<MonthlyTransferResponseDto> monthlyTransferList) {
        return new PaymentResponseDto(payment, isTotalOverZero, monthlyTransferList);
    }
}
