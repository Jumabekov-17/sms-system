package dasturlash.uz.dto.client;

import jakarta.validation.constraints.NotBlank;

public record RequestUpdatePassword(
        @NotBlank(message = "Phone is required") String phone,
        @NotBlank(message = "Old password is required") String oldPassword,
        @NotBlank(message = "New password is required") String newPassword) {
}
