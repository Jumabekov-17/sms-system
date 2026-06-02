package dasturlash.uz.dto.client.authentication;

import jakarta.validation.constraints.NotBlank;

public record RequestForgotPasswordClient(
        @NotBlank(message = "Phone must not be empty") String phone) {
}
