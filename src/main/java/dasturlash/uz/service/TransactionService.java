package dasturlash.uz.service;

import dasturlash.uz.entity.Client;
import dasturlash.uz.entity.Transaction;
import dasturlash.uz.enums.TransactionType;
import dasturlash.uz.repository.ClientRepository;
import dasturlash.uz.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void smsTransaction(Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found !"));

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        BigDecimal smsAmount = BigDecimal.valueOf(125.00);
        transaction.setAmount(smsAmount);
        transaction.setDescription("Sms transaction");
        transaction.setClientId(clientId);
        transaction.setBalanceAfter(client.getBalance().subtract(smsAmount));
        transaction.setBalanceBefore(client.getBalance());
        transactionRepository.save(transaction);

        clientService.deductionBalance(clientId, smsAmount);

    }

    public Page<Transaction> getTransactionsByClientId(Integer id, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return transactionRepository.getTransactionsByClientId(id,pageable);
    }
}
