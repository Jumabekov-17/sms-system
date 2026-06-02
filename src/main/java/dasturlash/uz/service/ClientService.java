package dasturlash.uz.service;

import dasturlash.uz.dto.client.service.ResponseDtoForClientInfo;
import dasturlash.uz.entity.Client;
import dasturlash.uz.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ResponseDtoForClientInfo clientProfile(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + id + " not found"));
        return toDto(client);
    }

    public ResponseDtoForClientInfo toDto(Client client) {
        ResponseDtoForClientInfo response = new ResponseDtoForClientInfo();
        response.setId(client.getId());
        response.setCompanyName(client.getCompanyName());
        response.setOwnerName(client.getOwnerName());
        response.setOwnerSurname(client.getOwnerSurname());
        response.setPhone(client.getPhone());
        response.setEmail(client.getEmail());
        response.setLogin(client.getLogin());
        response.setBalance(client.getBalance());
        response.setStatus(client.getStatus());
        response.setCreatedAt(client.getCreatedAt());
        return response;
    }
}
