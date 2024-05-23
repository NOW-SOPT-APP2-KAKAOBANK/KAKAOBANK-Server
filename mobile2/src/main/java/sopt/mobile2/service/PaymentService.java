package sopt.mobile2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.mobile2.domain.Transfer;
import sopt.mobile2.dto.MonthlyTransferRequestDto;
import sopt.mobile2.dto.MonthlyTransferResponseDto;
import sopt.mobile2.dto.PaymentResponse;
import sopt.mobile2.dto.PaymentResponseDto;
import sopt.mobile2.repository.TransferRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TransferRepository transferRepository;

    public PaymentResponseDto getMonthPayment(Long accountId, int month){
        List<Transfer> transfersForElse = transferRepository.findByMyAccountIdAndMonth(accountId, month);
        List<Transfer> transfersForMe = transferRepository.findByReceiveAccountIdAndMonth(accountId, month);

        long totalPaymentForElse = transfersForElse.stream().mapToLong(Transfer::getTransferAmount).sum();
        long totalPaymentForMe = transfersForMe.stream().mapToLong(Transfer::getTransferAmount).sum();

        long total = totalPaymentForMe-totalPaymentForElse;
        List<MonthlyTransferResponseDto> transferByMonth = getTransferByMonth(accountId, month);

        boolean isTotalOverZero = false;
        if(total >= 0) {
            isTotalOverZero = true;
        }
        return PaymentResponseDto.of(total, isTotalOverZero, transferByMonth);
    }

    public List<MonthlyTransferResponseDto> getTransferByMonth(Long accountId, int month) {
        List<MonthlyTransferResponseDto> result = new ArrayList<>();
        List<Transfer> findTransfer = transferRepository.findByMyAccountIdOrReceiveAccountIdAndMonthOrderByCreatedAtDesc(
                accountId, accountId, month);

        findTransfer.forEach(
                transfer -> {
                    //출금
                    if(transfer.getMyAccount().getId().equals(accountId)) {
                        result.add(new MonthlyTransferResponseDto(transfer.getReceiveAccount().getAccountName(),
                                parseLocalDateTimeToMonthAndDate(transfer.getCreatedAt()), -transfer.getTransferAmount(),transfer.getMyAccountBalance(), true, transfer.getHashTag()));
                        //입금
                    } else if (transfer.getReceiveAccount().getId().equals(accountId)){
                        result.add(new MonthlyTransferResponseDto(transfer.getMyAccount().getAccountName(),
                                parseLocalDateTimeToMonthAndDate(transfer.getCreatedAt()), transfer.getTransferAmount(),transfer.getReceiveAccountBalance(), false, transfer.getHashTag()));
                    }
                }
        );
        return result;
    }

    private String parseLocalDateTimeToMonthAndDate(LocalDateTime localDateTime) {
        String month = Integer.toString(localDateTime.getMonthValue());
        if(month.length() == 1) {
            month = "0" + month;
        }
        String day = Integer.toString(localDateTime.getDayOfMonth());
        if(day.length() == 1) {
            day = "0" + day;
        }
        return month + "." + day;
    }
}
