package dasturlash.uz.entity;

import dasturlash.uz.enums.SmsStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "client_id")
    private Integer clientId;
    @Column
    private String phone;
    @Column
    private String text;
    @Column
    private Double price = 125.00;
    @Column
    @Enumerated(EnumType.STRING)
    private SmsStatus status;
    @Column(name = "created_date")
    private LocalDateTime createdAt;
}
