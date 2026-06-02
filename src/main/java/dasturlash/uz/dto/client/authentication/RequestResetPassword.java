package dasturlash.uz.dto.client.authentication;

import jakarta.validation.constraints.NotBlank;

public record RequestResetPassword(
        @NotBlank(message = "Phone is required") String phone,
        @NotBlank(message = "Code is required")String code,
        @NotBlank(message = "New Password is required")String newPassword) {
}
