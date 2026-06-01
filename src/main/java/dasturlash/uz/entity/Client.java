package dasturlash.uz.entity;

import dasturlash.uz.enums.Status;
import dasturlash.uz.enums.Visible;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "owner_surname")
    private String ownerSurname;
    @Column(unique = true)
    private String phone;
    @Column
    private String email;
    @Column(unique = true)
    private String login;
    @Column
    private String password;
    @Column(precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status = Status.BLOCKED;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    @Enumerated(EnumType.STRING)
    private Visible visible = Visible.INACTIVE;
}
