package dasturlash.uz.controller;

import dasturlash.uz.entity.Transaction;
import dasturlash.uz.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/my/{id}")
    public ResponseEntity<Page<Transaction>> getAllTransactionsById(
            @PathVariable Integer id,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok(transactionService.getTransactionsByClientId(id,page,size));
    }
}
