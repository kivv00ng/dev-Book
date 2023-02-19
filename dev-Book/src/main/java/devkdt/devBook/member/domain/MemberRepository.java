package devkdt.devBook.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  //select * from member where slack_id='slackId1' and password='1234';
  @Query("select m from Member m where m.slackId =:slackId and m.password =:password")
  Optional<Member> findMemberForLogin(@Param("slackId") String slackId,
      @Param("password") String password);

}
