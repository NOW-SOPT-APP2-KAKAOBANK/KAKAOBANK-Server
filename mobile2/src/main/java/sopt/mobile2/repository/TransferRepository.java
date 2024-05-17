package sopt.mobile2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.mobile2.domain.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByMyAccountIdAndMonth(Long accountId, int month);
    List<Transfer> findByReceiveAccountIdAndMonth(Long accountId, int month);
    List<Transfer> findByMyAccountId(Long accountId);
    List<Transfer> findByMyAccountIdOrReceiveAccountIdAndMonthOrderByCreatedAtDesc(Long accountId, Long receiveAccountId,int month);
}
