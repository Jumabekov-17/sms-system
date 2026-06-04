package dasturlash.uz.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDtoForSmsInfo {
    private Integer id;
    private Integer clientId;
    private String phone;
    private String message;
    private LocalDateTime createdAt;
}
