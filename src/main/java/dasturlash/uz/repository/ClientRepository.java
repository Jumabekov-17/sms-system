package dasturlash.uz.repository;

import dasturlash.uz.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByLoginAndEmail(String login, String email);

    Optional<Client> findByLogin(String login);

    boolean existsByPhone(String phone);

    Client getClientByPhone(String phone);
}
