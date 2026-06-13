package dasturlash.uz.service;

import dasturlash.uz.dto.client.authentication.RequestForClientLogin;
import dasturlash.uz.dto.client.service.RequestForFillBalance;
import dasturlash.uz.dto.client.service.ResponseDtoForClientInfo;
import dasturlash.uz.entity.Client;
import dasturlash.uz.entity.Transaction;
import dasturlash.uz.enums.TransactionType;
import dasturlash.uz.repository.ClientRepository;
import dasturlash.uz.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public BigDecimal getBalanceByClientId(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + id + " not found"));
        return client.getBalance();
    }

    public BigDecimal fillBalance(RequestForFillBalance request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + request.clientId() + " not found"));

        BigDecimal balance = client.getBalance();
        BigDecimal newBalance = balance.add(BigDecimal.valueOf(request.balance()));
        client.setBalance(newBalance);
        clientRepository.save(client);

        Transaction transaction = new Transaction();
        transaction.setClientId(client.getId());
        transaction.setAmount(BigDecimal.valueOf(request.balance()));
        transaction.setBalanceAfter(newBalance);
        transaction.setBalanceBefore(balance);
        transaction.setDescription("Fill balance");
        transaction.setType(TransactionType.CREDIT);
        transactionRepository.save(transaction);

        return newBalance;

    }

    public void deductionBalance(Integer clientId, BigDecimal amount) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + clientId + " not found"));
        client.setBalance(client.getBalance().subtract(amount));
        clientRepository.save(client);
    }


    public Integer getClientIdByLoginAndPassword(RequestForClientLogin request) {
        Client client = clientRepository.findByLogin(request.login())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        if (!bCryptPasswordEncoder.matches(request.password(), client.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        return client.getId();
    }
}
