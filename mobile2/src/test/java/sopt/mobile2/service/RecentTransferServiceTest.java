package sopt.mobile2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sopt.mobile2.domain.Account;
import sopt.mobile2.domain.Transfer;
import sopt.mobile2.dto.RecentTransferResponse;
import sopt.mobile2.repository.BookMarkRepository;
import sopt.mobile2.repository.TransferRepository;
import sopt.mobile2.repository.AccountRepository;
import sopt.mobile2.domain.BookMark;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecentTransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private BookMarkRepository bookMarkRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RecentTransferService recentTransferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecentTransferList() {
        Long accountId = 1L;
        Account account1 = new Account();
        account1.setId(2L);
        account1.setAccountName("Account1");
        account1.setAccountNumber(111111);

        Account account2 = new Account();
        account2.setId(3L);
        account2.setAccountName("Account2");
        account2.setAccountNumber(222222);

        Transfer transfer1 = new Transfer();
        transfer1.setReceiveAccount(account1);
        transfer1.setCreatedAt(LocalDateTime.now());

        Transfer transfer2 = new Transfer();
        transfer2.setReceiveAccount(account2);
        transfer2.setCreatedAt(LocalDateTime.now());

        when(transferRepository.findByMyAccountId(accountId)).thenReturn(List.of(transfer1, transfer2));
        when(bookMarkRepository.existsByMarkedAccountId(anyLong())).thenReturn(true);

        List<RecentTransferResponse> result = recentTransferService.recentTransferList(accountId);

        assertEquals(2, result.size());
    }

    @Test
    public void testAddBookMark() {
        Long myAccountId = 1L;
        Long markedAccountId = 2L;
        Account myAccount = new Account();
        myAccount.setId(myAccountId);
        Account markedAccount = new Account();
        markedAccount.setId(markedAccountId);

        when(accountRepository.findById(myAccountId)).thenReturn(Optional.of(myAccount));
        when(accountRepository.findById(markedAccountId)).thenReturn(Optional.of(markedAccount));

        recentTransferService.addBookMark(myAccountId, markedAccountId);

        verify(bookMarkRepository, times(1)).save(any(BookMark.class));
    }

    @Test
    public void testRemoveBookMark() {
        Long myAccountId = 1L;
        Long markedAccountId = 2L;
        BookMark bookMark = new BookMark();
        bookMark.setId(1L);

        when(bookMarkRepository.findByMyAccountIdAndMarkedAccountId(myAccountId, markedAccountId)).thenReturn(Optional.of(bookMark));

        recentTransferService.removeBookMark(myAccountId, markedAccountId);

        verify(bookMarkRepository, times(1)).delete(bookMark);
    }
}