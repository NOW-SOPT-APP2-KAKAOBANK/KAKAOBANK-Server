package sopt.mobile2.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account myAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account markedAccount;
}
