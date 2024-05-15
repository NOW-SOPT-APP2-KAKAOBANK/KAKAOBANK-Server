package sopt.mobile2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.mobile2.domain.Account;
import sopt.mobile2.domain.Transfer;
import sopt.mobile2.dto.RecentTransferResponse;
import sopt.mobile2.repository.BookmarkRepository;
import sopt.mobile2.repository.TransferRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecentTransferService {

    private final TransferRepository transferRepository;
    private final BookmarkRepository bookmarkRepository;

    public List<RecentTransferResponse> recentTransferList(Long accountId) {
        List<Transfer> transfers = transferRepository.findByMyAccountId(accountId);

        // LinkedHashMap을 사용하여 중복된 수신 계좌를 최신 정보로 업데이트하고 유지합니다.
        Map<Long, RecentTransferResponse> uniqueTransfers = transfers.stream()
                .collect(Collectors.toMap(
                        transfer -> transfer.getReceiveAccount().getId(),
                        transfer -> new RecentTransferResponse(
                                transfer.getReceiveAccount().getAccountName(),
                                transfer.getReceiveAccount().getAccountNumber(),
                                isFavorite(transfer.getReceiveAccount()),
                                transfer.getCreatedAt()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        return uniqueTransfers.values().stream()
                .sorted(Comparator.comparing(RecentTransferResponse::isAccountLike).reversed()
                        .thenComparing(RecentTransferResponse::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    private boolean isFavorite(Account account){
        return bookmarkRepository.existsByMarkedAccountId(account.getId());
    }
}
