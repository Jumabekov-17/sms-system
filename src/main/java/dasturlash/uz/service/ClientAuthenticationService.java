package dasturlash.uz.service;

import dasturlash.uz.dto.client.authentication.RequestForClientLogin;
import dasturlash.uz.dto.client.authentication.RequestForRegisterClient;
import dasturlash.uz.dto.client.authentication.ResponseDtoForClient;
import dasturlash.uz.entity.Client;
import dasturlash.uz.repository.ClientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthenticationService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ClientRepository clientRepository;

    public String registerClient(RequestForRegisterClient request) {
        boolean response = validationClientRequest(request.login(), request.email());
        if (response) {
            throw new ValidationException("Invalid Username or password");
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
        client.setPassword(bCryptPasswordEncoder.encode(request.password()));
        client.setPhone(request.phone());
        client.setEmail(request.email());
        clientRepository.save(client);
        return "User registered successfully";
    }


    public boolean validationClientRequest(String login, String email) {
        return clientRepository.existsByLoginAndEmail(login, email);
    }

    public ResponseDtoForClient clientLogin(RequestForClientLogin request) {
        Client client = clientRepository.findByLogin(request.login())
                .orElseThrow(() -> new IllegalArgumentException("Invalid login or password"));
        if (!bCryptPasswordEncoder.matches(request.password(),client.getPassword())) {
            throw new ValidationException("Invalid login or password");
        }

        ResponseDtoForClient responseDto = new ResponseDtoForClient();
        responseDto.setLogin(client.getLogin());
        responseDto.setPhone(client.getPhone());
        responseDto.setEmail(client.getEmail());
        responseDto.setCompanyName(client.getCompanyName());
        responseDto.setOwnerName(client.getOwnerName());
        responseDto.setOwnerSurname(client.getOwnerSurname());
        responseDto.setId(client.getId());
        return responseDto;
    }
}
