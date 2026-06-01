package dasturlash.uz.service;

import dasturlash.uz.dto.RequestForRegisterClient;
import dasturlash.uz.entity.Client;
import dasturlash.uz.repository.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String registerClient(@Valid RequestForRegisterClient request) {
        boolean response = validationClientRequest(request.login(), request.email());
        if (response) {
            throw new ValidationException("Invalid login or password");
        }
        if (request.password().length() < 4) {
            throw new ValidationException("Password length too short");
        }
        if (request.phone().length() < 11) {
            throw new ValidationException("Phone length too short");
        }
        Client client = new Client();
        client.setCompanyName(request.companyName());
        client.setOwnerName(request.ownerName());
        client.setOwnerSurname(request.ownerSurname());
        client.setLogin(request.login());
        client.setPassword(request.password());
        client.setPhone(request.phone());
        client.setEmail(request.email());
        clientRepository.save(client);
        return "User registered successfully";
    }


    public boolean validationClientRequest(String login, String email) {
        return clientRepository.existsByLoginAndEmail(login, email);
    }
}
