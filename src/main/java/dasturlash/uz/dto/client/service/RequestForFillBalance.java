package dasturlash.uz.dto.client.service;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record RequestForFillBalance(
       @Positive(message = "Client id is not be negative number") Integer clientId,
       @PositiveOrZero(message = "Balance is not be negative number") Long balance) {
}
