package sopt.mobile2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Transfer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //송금 하는 계좌
    @ManyToOne(fetch = FetchType.LAZY)
    private Account myAccount;

    //송금 받는 계좌
    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiveAccount;

    //거래액
    private int transferAmount;

    //거래 후 내 계좌의 남은 잔액
    private int myAccountBalance;

    private int month;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
