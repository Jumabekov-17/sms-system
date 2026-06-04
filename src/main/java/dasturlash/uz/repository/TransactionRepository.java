package dasturlash.uz.repository;

import dasturlash.uz.entity.Transaction;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Page<Transaction> getTransactionsByClientId(Integer id, Pageable pageable);

    Transaction getTransactionsByClientId(Integer id);
}
