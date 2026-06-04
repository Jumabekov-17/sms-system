package dasturlash.uz.controller;

import dasturlash.uz.dto.client.service.ResponseDtoForClientInfo;
import dasturlash.uz.dto.sms.ResponseDtoForSmsInfo;
import dasturlash.uz.entity.Transaction;
import dasturlash.uz.enums.Status;
import dasturlash.uz.service.AdminService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/get-clients")
    public ResponseEntity<Page<ResponseDtoForClientInfo>> getClients(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "status", required = false) Status status) {
        return ResponseEntity.ok(adminService.getClientsInfo(page, size, search, status));
    }

    @GetMapping("/client-by-id/{id}")
    public ResponseEntity<ResponseDtoForClientInfo> getClientById(
            @PathVariable @Min(1) Integer id) {
        return ResponseEntity.ok(adminService.getClientById(id));
    }

    @PutMapping("/block-client/{id}")
    public ResponseEntity<?> blockClient(
            @PathVariable @Min(1) Integer id) {
        return ResponseEntity.ok(adminService.blockClientById(id));
    }

    @PutMapping("/unblock-client/{id}")
    public ResponseEntity<?> unblockClient(
            @PathVariable @Min(1) Integer id) {
        return ResponseEntity.ok(adminService.unblockClient(id));
    }

    @GetMapping("all-sms")
    public ResponseEntity<Page<ResponseDtoForSmsInfo>> getAllSms(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "2") int size) {
        return ResponseEntity.ok(adminService.getAllSms(page, size));
    }

    @GetMapping("sms-by-id/{id}")
    public ResponseEntity<ResponseDtoForSmsInfo> getSmsById(
            @PathVariable @Min(1) Integer id) {
        return ResponseEntity.ok(adminService.getSmsInfoById(id));
    }


    @GetMapping("/transactions")
    public ResponseEntity<Page<Transaction>> getAllTransactions(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "2") int size) {
        return ResponseEntity.ok(adminService.getAllTransactions(page, size));
    }

    @GetMapping("/transaction-by-id/{id}")
    public ResponseEntity<Transaction> getTransactionById(
            @PathVariable @Min(1) Integer id){
        return ResponseEntity.ok(adminService.getTransactionById(id));
    }
}
