package dasturlash.uz.controller;

import dasturlash.uz.dto.RequestForRegisterClient;
import dasturlash.uz.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/registration")
    public ResponseEntity<?> clientRegister(@RequestBody @Valid RequestForRegisterClient request) {
        return ResponseEntity.ok(clientService.registerClient(request));
    }
}
