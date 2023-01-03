package devkdt.devBook.entity;

import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    private String regExpPhone = "010-\\d{4}-\\d{4}";

    @Test
    void validate() {
        String inputString = "010-1234-1234";
        boolean check = Pattern.matches(regExpPhone, inputString);
        System.out.println(check);

        new Member("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
    }

    @Test
    void login() {
        Member member = new Member("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        memberRepository.save(member);
        Optional<Member> loginResult = memberRepository.findMemberForLogin(member.getSlackId(), member.getPassword());
        Optional<Member> loginFailResult1 = memberRepository.findMemberForLogin("zzzz", member.getPassword());
        Optional<Member> loginFailResult2 = memberRepository.findMemberForLogin(member.getSlackId(), "zzzz");

        assertThat(loginResult.isPresent()).isTrue();
        assertThat(loginFailResult1.isPresent()).isFalse();
        assertThat(loginFailResult2.isPresent()).isFalse();
    }

    @Test
    void approveTest() {

    }

}
