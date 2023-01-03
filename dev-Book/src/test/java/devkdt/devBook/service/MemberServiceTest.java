package devkdt.devBook.service;

import devkdt.devBook.joinRequest.dto.MemberJoinResponse;
import devkdt.devBook.member.application.MemberService;
import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.joinRequest.domain.TemporaryMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    /*
        @Transactional
    public MemberJoinResponse approveJoin(TemporaryMember temporaryMember) {
        Member saveMember = memberRepository.save(temporaryMember.toMember());
        return new MemberJoinResponse(saveMember);
    }
     */
    @Test
    void approveJoinTest() {
        TemporaryMember temporaryMember = new TemporaryMember("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        MemberJoinResponse memberJoinResponse = memberService.approveJoin(temporaryMember);

        Assertions.assertThat(memberJoinResponse.getName()).isEqualTo("name1");
        System.out.println("#### =>" + memberJoinResponse);
    }

}