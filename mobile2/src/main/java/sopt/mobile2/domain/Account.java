package sopt.mobile2.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String accountName;

    private int balance;

    private int accountNumber;
}
