package dasturlash.uz.entity;

import dasturlash.uz.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "client_id")
    private Integer clientId;
    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column
    private BigDecimal amount;
    @Column
    private String description;
    @Column
    private BigDecimal balanceBefore;
    @Column
    private BigDecimal balanceAfter;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
