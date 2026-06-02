package dasturlash.uz.dto.client.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDtoForClient {
    private Integer id;
    private String companyName;
    private String ownerName;
    private String ownerSurname;
    private String phone;
    private String email;
    private String login;
}
