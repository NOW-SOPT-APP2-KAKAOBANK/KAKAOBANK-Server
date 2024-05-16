package sopt.mobile2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_account_id", nullable = false)
    private Account myAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marked_account_id", nullable = false)
    private Account markedAccount;
}
