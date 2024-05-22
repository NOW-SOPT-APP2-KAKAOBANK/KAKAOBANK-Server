package sopt.mobile2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.mobile2.domain.BookMark;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
    boolean existsByMarkedAccountId(Long accountId);
    boolean existsByMyAccountIdAndMarkedAccountId(Long myAccountId, Long markedAccountId);
    Optional<BookMark> findByMyAccountIdAndMarkedAccountId(Long myAccountId, Long markedAccountId);
}

