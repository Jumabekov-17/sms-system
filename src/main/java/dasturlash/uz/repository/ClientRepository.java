package dasturlash.uz.repository;

import dasturlash.uz.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByLoginAndEmail(String login, String email);
}
