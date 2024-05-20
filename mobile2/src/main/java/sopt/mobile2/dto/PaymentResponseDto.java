package sopt.mobile2.dto;

import java.util.List;

public record PaymentResponseDto(int payment, boolean isTotalOverZero, List<MonthlyTransferResponseDto> montlyTransferList) {

    public static PaymentResponseDto of(int payment, boolean isTotalOverZero, List<MonthlyTransferResponseDto> montlyTransferList) {
        return new PaymentResponseDto(payment, isTotalOverZero, montlyTransferList);
    }
}
