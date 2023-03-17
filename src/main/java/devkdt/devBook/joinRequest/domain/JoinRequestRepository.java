package devkdt.devBook.joinRequest.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {

  @Query("select j from JoinRequest j where j.temporaryMember.phoneNumber =:phoneNumber")
  Optional<JoinRequest> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
