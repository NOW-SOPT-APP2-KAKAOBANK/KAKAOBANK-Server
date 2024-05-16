package sopt.mobile2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.mobile2.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
