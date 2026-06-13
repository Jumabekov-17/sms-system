package dasturlash.uz.repository;

import dasturlash.uz.entity.Client;
import dasturlash.uz.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByLoginAndEmail(String login, String email);

    Optional<Client> findByLogin(String login);

    boolean existsByPhone(String phone);

    Client getClientByPhone(String phone);

    @Query("select c from Client c" +
            " where (:search is null or" +
            " lower(c.ownerName) like :search or " +
            " lower(c.ownerSurname) like :search or " +
            " lower(c.email) like :search or " +
            " lower(c.phone) like :search or " +
            " lower(c.login) like :search or " +
            " lower(c.companyName) like :search)" +
            " and (:status is null or c.status = :status)")
    Page<Client> findAllWithFilters(@Param("search") String search, @Param("status") Status status, Pageable pageable);


    @Modifying
    @Transactional
    @Query("update Client c " +
            "set c.status = :status " +
            "where c.id = :id")
    void blockUserStatus(@Param("id") Integer id, @Param("status") Status status);


    @Modifying
    @Transactional
    @Query("update Client c " +
            " set c.status = :status" +
            " where c.id = :id")
    void unblockClientWithId(@Param("id") Integer id, @Param("status") Status status);


    @Query("select c.id from Client c" +
            " where c.login =:login" +
            " and c.password =:password")
    Integer getClientByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
