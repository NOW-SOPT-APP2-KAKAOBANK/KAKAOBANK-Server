package sopt.mobile2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.mobile2.domain.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByMemberId(Long memberId);
}
