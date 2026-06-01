package dasturlash.uz.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestForRegisterClient(
        @NotBlank(message = "Company Name is required") String companyName,
        @NotBlank(message = "Owner Name is required") String ownerName,
        @NotBlank(message = "Owner Surname is required") String ownerSurname,
        @NotBlank(message = "Phone is required") String phone,
        @Email(message = "Email is required") String email,
        @NotBlank(message = "Login is required") String login,
        @NotBlank(message = "Password is required") String password) {
}
