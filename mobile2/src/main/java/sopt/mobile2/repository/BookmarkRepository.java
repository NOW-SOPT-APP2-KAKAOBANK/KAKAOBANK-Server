package sopt.mobile2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.mobile2.domain.BookMark;

public interface BookmarkRepository extends JpaRepository<BookMark, Long> {
    boolean existsByMarkedAccountId(Long accountId);
}

