package sopt.mobile2.dto;

import java.util.List;

public record PaymentResponseDto(int payment, List<MonthlyTransferResponseDto> montlyTransferList) {

    public static PaymentResponseDto of(int payment, List<MonthlyTransferResponseDto> montlyTransferList) {
        return new PaymentResponseDto(payment, montlyTransferList);
    }
}
