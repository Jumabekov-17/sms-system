package dasturlash.uz.service;

import dasturlash.uz.dto.client.service.ResponseDtoForClientInfo;
import dasturlash.uz.dto.sms.ResponseDtoForSmsInfo;
import dasturlash.uz.entity.Client;
import dasturlash.uz.entity.Sms;
import dasturlash.uz.entity.Transaction;
import dasturlash.uz.enums.Status;
import dasturlash.uz.repository.ClientRepository;
import dasturlash.uz.repository.SmsRepository;
import dasturlash.uz.repository.TransactionRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<ResponseDtoForClientInfo> getClientsInfo(int page, int size, String search, Status status) {
        Pageable pageable = PageRequest.of(page - 1, size);
        String searchPattern = search == null ? null : "%" + search.toLowerCase() + "%";
        Page<Client> clients = clientRepository.findAllWithFilters(searchPattern, status, pageable);
        return clients.map(this::toDto);
    }

    public ResponseDtoForClientInfo getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + id + " not found"));
        return toDto(client);
    }


    public ResponseDtoForClientInfo toDto(Client client) {
        return new ResponseDtoForClientInfo(
                client.getId(),
                client.getCompanyName(),
                client.getOwnerName(),
                client.getOwnerSurname(),
                client.getPhone(),
                client.getEmail(),
                client.getLogin(),
                client.getBalance(),
                client.getStatus(),
                client.getCreatedAt()
        );
    }

    public boolean blockClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + id + " not found"));

        if (client.getStatus().equals(Status.BLOCKED)) {
            throw new IllegalArgumentException("Client with id " + id + " is already BLOCKED");
        }

        clientRepository.blockUserStatus(id, Status.BLOCKED);
        return true;
    }

    public boolean unblockClient(@Min(1) Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + id + " not found"));

        if (client.getStatus().equals(Status.ACTIVE)) {
            throw new IllegalArgumentException("Client with id " + id + " is already ACTIVE");
        }

        clientRepository.unblockClientWithId(id, Status.ACTIVE);
        return true;
    }

    public Page<ResponseDtoForSmsInfo> getAllSms(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Sms> smsPage = smsRepository.findAll(pageable);
        return smsPage.map(sms -> new ResponseDtoForSmsInfo(
                sms.getId(),
                sms.getClientId(),
                sms.getPhone(),
                sms.getText(),
                sms.getCreatedAt()
        ));
    }

    public ResponseDtoForSmsInfo getSmsInfoById(@Min(1) Integer id) {
        ResponseDtoForSmsInfo smsById = smsRepository.getSmsById(id);
        if (smsById == null) {
            throw new IllegalArgumentException("SMS with id " + id + " not found");
        }
        return smsById;
    }

    public Page<Transaction> getAllTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return transactionRepository.findAll(pageable);
    }

    public Transaction getTransactionById(@Min(1) Integer id) {
        Transaction transaction = transactionRepository.getTransactionsByClientId(id);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction with id " + id + " not found");
        }
        return transaction;
    }
}
