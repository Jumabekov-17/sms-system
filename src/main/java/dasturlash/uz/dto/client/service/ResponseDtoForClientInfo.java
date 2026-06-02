package dasturlash.uz.dto.client.service;

import dasturlash.uz.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseDtoForClientInfo {
    private Integer id;
    private String companyName;
    private String ownerName;
    private String ownerSurname;
    private String phone;
    private String email;
    private String login;
    private BigDecimal balance;
    private Status status;
    private LocalDateTime createdAt;
}
