package dasturlash.uz.entity;

import dasturlash.uz.enums.Role;
import dasturlash.uz.enums.Status;
import dasturlash.uz.enums.Visible;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Visible visible = Visible.INACTIVE;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status = Status.BLOCKED;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
}
