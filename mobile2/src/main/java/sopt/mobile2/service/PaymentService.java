package sopt.mobile2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.mobile2.domain.Transfer;
import sopt.mobile2.dto.PaymentResponse;
import sopt.mobile2.repository.TransferRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TransferRepository transferRepository;

    public PaymentResponse getMonthPayment(Long accountId, int month){
        List<Transfer> transfersForElse = transferRepository.findByMyAccountIdAndMonth(accountId, month);
        List<Transfer> transfersForMe = transferRepository.findByReceiveAccountIdAndMonth(accountId, month);

        int totalPaymentForElse = transfersForElse.stream().mapToInt(Transfer::getTransferAmount).sum();
        int totalPaymentForMe = transfersForMe.stream().mapToInt(Transfer::getTransferAmount).sum();

        int total = totalPaymentForMe-totalPaymentForElse;

        return new PaymentResponse(month, total);
    }
}
