package sopt.mobile2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sopt.mobile2.domain.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByMyAccountIdAndMonth(Long accountId, int month);
    List<Transfer> findByReceiveAccountIdAndMonth(Long accountId, int month);
    List<Transfer> findByMyAccountId(Long accountId);
//    List<Transfer> findByMyAccountIdOrReceiveAccountIdAndMonthOrderByCreatedAtDesc(Long accountId, Long receiveAccountId,int month);
    @Query("select t from Transfer t where" +
            " t.month =:month" +
            " and (t.myAccount.id = :accountId" +
            " or t.receiveAccount.id =:receiveAccountId)" +
            " order by t.createdAt desc")
    List<Transfer> findByMyAccountIdOrReceiveAccountIdAndMonthOrderByCreatedAtDesc(@Param("accountId") Long accountId,
                                                                                   @Param("receiveAccountId") Long receiveAccountId,
                                                                                   @Param("month") int month);
}
