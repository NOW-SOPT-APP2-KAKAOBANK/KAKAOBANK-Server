package sopt.mobile2.domain;

import jakarta.persistence.*;
import lombok.Getter;

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

    private int month;
}
