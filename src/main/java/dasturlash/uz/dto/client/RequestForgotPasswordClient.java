package dasturlash.uz.dto.client;

import jakarta.validation.constraints.NotBlank;

public record RequestForgotPasswordClient(
        @NotBlank(message = "Phone must not be empty") String phone) {
}
