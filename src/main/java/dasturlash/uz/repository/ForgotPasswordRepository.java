package dasturlash.uz.repository;

import dasturlash.uz.entity.ClientForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepository extends JpaRepository<ClientForgotPassword, Integer> {
    boolean existsByPhoneNumberAndCode(String phoneNumber, String phoneNumber1);
}
