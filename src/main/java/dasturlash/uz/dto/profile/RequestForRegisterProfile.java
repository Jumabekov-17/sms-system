package dasturlash.uz.dto.profile;

import jakarta.validation.constraints.NotBlank;

public record RequestForRegisterProfile(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Surname is required") String surname,
        @NotBlank(message = "Login is required") String login,
        @NotBlank(message = "Password is required") String password) {
}
