package dasturlash.uz.service;

import dasturlash.uz.dto.sms.RequestForSendSms;
import dasturlash.uz.entity.Client;
import dasturlash.uz.entity.Sms;
import dasturlash.uz.enums.SmsStatus;
import dasturlash.uz.enums.Status;
import dasturlash.uz.projections.GetSmsShortInfo;
import dasturlash.uz.repository.ClientRepository;
import dasturlash.uz.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SmsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private TransactionService transactionService;

    public String sendSms(RequestForSendSms request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        if (client.getStatus().equals(Status.BLOCKED)) {
            throw new IllegalArgumentException("Client is blocked");
        }

        BigDecimal smsAmount = BigDecimal.valueOf(125);
        int comparison = client.getBalance().compareTo(smsAmount);
        if (comparison < 0) {
            throw new IllegalArgumentException("SMS amount must be greater than or equal to 125");
        }
        Sms sms = new Sms();
        sms.setClientId(client.getId());
        sms.setPhone(request.phoneNumber());
        sms.setText(request.message());
        sms.setStatus(SmsStatus.SENT);
        smsRepository.save(sms);

        transactionService.smsTransaction(client.getId());

        return "Sms Successfully sent";
    }

    public Page<GetSmsShortInfo> getSmsByClientId(Integer id, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Sms> smsPage = smsRepository.getSmsByClientId(id, pageable);
        return smsPage.map(sms -> new GetSmsShortInfo(
                sms.getId(),
                sms.getClientId(),
                sms.getPhone(),
                sms.getText(),
                sms.getCreatedAt()
        ));
    }
}
