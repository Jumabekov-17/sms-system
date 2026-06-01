package dasturlash.uz.service;

import dasturlash.uz.dto.client.RequestForgotPasswordClient;
import dasturlash.uz.dto.client.RequestResetPassword;
import dasturlash.uz.dto.client.RequestUpdatePassword;
import dasturlash.uz.entity.Client;
import dasturlash.uz.entity.ClientForgotPassword;
import dasturlash.uz.repository.ClientRepository;
import dasturlash.uz.repository.ForgotPasswordRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerificationService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String forgotPassword(RequestForgotPasswordClient request) {
        boolean response = clientRepository.existsByPhone(request.phone());
        if (!response) {
            throw new IllegalArgumentException("Invalid phone number provided");
        }

        String code = String.valueOf(new Random().nextInt(999999));
        ClientForgotPassword clientForgotPassword = new ClientForgotPassword();
        clientForgotPassword.setPhoneNumber(request.phone());
        clientForgotPassword.setCode(code);
        forgotPasswordRepository.save(clientForgotPassword);
        return "Code is successfully sent to client phone";
    }

    public String resetPassword(RequestResetPassword request) {
        boolean response = forgotPasswordRepository.existsByPhoneNumberAndCode(request.phone(), request.code());
        if (!response) {
            throw new IllegalArgumentException("Invalid phone number or code provided");
        }

        Client client = clientRepository.getClientByPhone(request.phone());

        String newPassword = bCryptPasswordEncoder.encode(request.newPassword());

        client.setPassword(newPassword);
        clientRepository.save(client);
        return "Password is successfully changed";
    }

    public String updatePassword(RequestUpdatePassword request) {
        Client client = clientRepository.getClientByPhone(request.phone());

        boolean result = bCryptPasswordEncoder.matches(request.oldPassword(), client.getPassword());
        if (!result) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        String newPassword = bCryptPasswordEncoder.encode(request.newPassword());
        client.setPassword(newPassword);
        clientRepository.save(client);
        return "Password is successfully updated";
    }
}
