package dasturlash.uz.entity;

import dasturlash.uz.enums.Status;
import dasturlash.uz.enums.Visible;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
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
    @Column
    private String phone;
    @Column
    private String email;
    @Column(unique = true)
    private String login;
    @Column
    private String password;
    @Column(precision = 19, scale = 2)
    private BigDecimal balance;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_date")
    private LocalDateTime createdAt;
    @Column
    @Enumerated(EnumType.STRING)
    private Visible visible;
}
