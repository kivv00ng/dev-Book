package devkdt.devBook.service;

import devkdt.devBook.dto.LoginRequest;
import devkdt.devBook.dto.MemberJoinResponse;
import devkdt.devBook.entity.Member;
import devkdt.devBook.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member login(LoginRequest loginRequest) {
        Optional<Member> loginMember = memberRepository.findMemberForLogin(loginRequest.getSlackId(), loginRequest.getPassword());
        return loginMember.orElseThrow(() -> new RuntimeException("로그인 실패 Exception만들기"));
    }

    @Transactional
    public MemberJoinResponse save(Member member) {
        Member saveMember = memberRepository.save(member);
        return new MemberJoinResponse(saveMember);
    }
}
