package dasturlash.uz.projections;

import java.time.LocalDateTime;


public record GetSmsShortInfo(
        Integer smsId,
        Integer clientId,
        String phoneNumber,
        String text,
        LocalDateTime createdAt) {

}
