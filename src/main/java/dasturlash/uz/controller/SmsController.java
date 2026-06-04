package dasturlash.uz.controller;

import dasturlash.uz.dto.sms.RequestForSendSms;
import dasturlash.uz.projections.GetSmsShortInfo;
import dasturlash.uz.service.SmsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
@Validated
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<?> sendSms(@RequestBody @Valid RequestForSendSms request) {
        return ResponseEntity.ok(smsService.sendSms(request));
    }

    @GetMapping("/my-sms")
    public ResponseEntity<Page<GetSmsShortInfo>> getMySms(
            @RequestParam("id") @Min(1) Integer id,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "2") int size) {
        return ResponseEntity.ok(smsService.getSmsByClientId(id, page, size));
    }
}
