package dasturlash.uz.dto.sms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RequestForSendSms(
        @Positive(message = "Client id is not be negative nums") Integer clientId,
        @NotBlank(message = "Phone number is required") String phoneNumber,
        @NotBlank(message = "Text is not be empty") String text) {
}
