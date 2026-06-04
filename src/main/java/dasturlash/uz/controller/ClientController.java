package dasturlash.uz.controller;

import dasturlash.uz.dto.client.service.RequestForFillBalance;
import dasturlash.uz.service.ClientService;
import dasturlash.uz.service.SmsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@Validated
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private SmsService smsService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.clientProfile(id));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<?> getBalance(@PathVariable @Min(value = 1, message = "Id is not negative number") Integer id) {
        return ResponseEntity.ok(clientService.getBalanceByClientId(id));
    }

    @PostMapping("/fill-balance")
    public ResponseEntity<?> fillBalance(@RequestBody @Valid RequestForFillBalance request) {
        return ResponseEntity.ok(clientService.fillBalance(request));
    }
}
