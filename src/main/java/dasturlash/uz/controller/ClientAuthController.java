package dasturlash.uz.controller;

import dasturlash.uz.dto.client.*;
import dasturlash.uz.service.ClientService;
import dasturlash.uz.service.VerificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientAuthController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/registration")
    public ResponseEntity<?> clientRegister(@RequestBody @Valid RequestForRegisterClient request) {
        return ResponseEntity.ok(clientService.registerClient(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody @Valid RequestForClientLogin request) {
        return ResponseEntity.ok(clientService.clientLogin(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid RequestForgotPasswordClient request) {
        return ResponseEntity.ok(verificationService.forgotPassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid RequestResetPassword request) {
        return ResponseEntity.ok(verificationService.resetPassword(request));
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid RequestUpdatePassword request) {
        return ResponseEntity.ok(verificationService.updatePassword(request));
    }
}
