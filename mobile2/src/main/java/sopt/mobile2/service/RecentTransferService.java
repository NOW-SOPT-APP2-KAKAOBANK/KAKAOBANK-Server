package sopt.mobile2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.mobile2.domain.Account;
import sopt.mobile2.domain.BookMark;
import sopt.mobile2.domain.Transfer;
import sopt.mobile2.dto.RecentTransferResponse;
import sopt.mobile2.repository.AccountRepository;
import sopt.mobile2.repository.BookMarkRepository;
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
    private final BookMarkRepository bookmarkRepository;
    private final AccountRepository accountRepository;

    public List<RecentTransferResponse> recentTransferList(Long accountId) {
        List<Transfer> transfers = transferRepository.findByMyAccountId(accountId);

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

    @Transactional
    public void addBookMark(Long myAccountId, Long markedAccountId) {
        Account myAccount = accountRepository.findById(myAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid myAccount ID"));
        Account markedAccount = accountRepository.findById(markedAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid markedAccount ID"));

        BookMark bookMark = BookMark.builder()
                .myAccount(myAccount)
                .markedAccount(markedAccount)
                .build();

        bookmarkRepository.save(bookMark);
    }

    @Transactional
    public void removeBookMark(Long myAccountId, Long markedAccountId) {
        BookMark bookMark = bookmarkRepository.findByMyAccountIdAndMarkedAccountId(myAccountId, markedAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
        bookmarkRepository.delete(bookMark);
    }
}
