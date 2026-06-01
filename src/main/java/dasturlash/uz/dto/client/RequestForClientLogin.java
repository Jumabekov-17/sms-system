package dasturlash.uz.dto.client;

import jakarta.validation.constraints.NotBlank;

public record RequestForClientLogin(
        @NotBlank(message = "Login must not be empty.") String login,
        @NotBlank(message = "Password must not be empty.") String password) {
}
