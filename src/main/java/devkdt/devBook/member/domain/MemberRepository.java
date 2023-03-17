package devkdt.devBook.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query("select m from Member m where m.slackId =:slackId and m.password =:password")
  Optional<Member> findMemberForLogin(@Param("slackId") String slackId,
      @Param("password") String password);

//  Optional<Member> findMemberByPhoneNumber(String phoneNumber);

  Boolean existsByPhoneNumber(String phoneNumber);

}
