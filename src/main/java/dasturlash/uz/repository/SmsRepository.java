package dasturlash.uz.repository;

import dasturlash.uz.dto.sms.ResponseDtoForSmsInfo;
import dasturlash.uz.entity.Sms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmsRepository extends JpaRepository<Sms, Integer> {
    Page<Sms> getSmsByClientId(@Param("id") Integer id, Pageable pageable);


    @Query("select s from Sms s " +
            " where s.id = :id")
    ResponseDtoForSmsInfo getSmsById(@Param("id") Integer id);
}
