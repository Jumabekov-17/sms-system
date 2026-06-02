package dasturlash.uz.dto.profile;

import jakarta.validation.constraints.NotBlank;

public record RequestForLoginProfile(
        @NotBlank(message = "Login is required") String login,
        @NotBlank(message = "Password is required") String password
) {
}
