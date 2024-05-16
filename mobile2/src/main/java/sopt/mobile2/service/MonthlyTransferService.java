package sopt.mobile2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.mobile2.domain.Transfer;
import sopt.mobile2.dto.MonthlyTransferRequestDto;
import sopt.mobile2.dto.MonthlyTransferResponseDto;
import sopt.mobile2.repository.TransferRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyTransferService {

    private final TransferRepository transferRepository;

    public List<MonthlyTransferResponseDto> getTransferByMonth(int month, MonthlyTransferRequestDto requestDto) {
        List<MonthlyTransferResponseDto> result = new ArrayList<>();
        List<Transfer> findTransfer = transferRepository.findByMyAccountIdOrReceiveAccountIdAndMonthOrderByCreatedAtDesc(
                requestDto.accountId(), requestDto.accountId(), month);

        findTransfer.forEach(
                transfer -> {
                    if(transfer.getMyAccount().getId().equals(requestDto.accountId())) {
                        result.add(new MonthlyTransferResponseDto(transfer.getReceiveAccount().getAccountName(),
                                parseLocalDateTimeToMonthAndDate(transfer.getCreatedAt()), -transfer.getTransferAmount(),transfer.getMyAccountBalance()));
                    } else if (transfer.getReceiveAccount().getId().equals(requestDto.accountId())){
                        result.add(new MonthlyTransferResponseDto(transfer.getMyAccount().getAccountName(),
                                parseLocalDateTimeToMonthAndDate(transfer.getCreatedAt()), transfer.getTransferAmount(),transfer.getMyAccountBalance()));
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

        return month + "." + day;
    }

}
