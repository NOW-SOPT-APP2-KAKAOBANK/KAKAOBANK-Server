package sopt.mobile2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.mobile2.domain.Account;
import sopt.mobile2.dto.AccountInfoResponseDto;
import sopt.mobile2.dto.AccountResponseDto;
import sopt.mobile2.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountResponseDto> getMyAccountList(Long memberId) {
        List<Account> findAccountList = accountRepository.findAllByMemberId(memberId);
        return findAccountList.stream().map(
                account -> AccountResponseDto.of(account)
        ).collect(Collectors.toList());
    }

    public AccountInfoResponseDto getMyAccountInfo(Long accountId){
        Account myAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid myAccount ID"));
        return AccountInfoResponseDto.of(myAccount);
    }

}
