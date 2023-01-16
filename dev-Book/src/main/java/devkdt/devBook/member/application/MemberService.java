package devkdt.devBook.member.application;

import devkdt.devBook.member.dto.LoginRequest;
import devkdt.devBook.joinRequest.dto.MemberJoinResponse;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import devkdt.devBook.joinRequest.domain.TemporaryMember;
import devkdt.devBook.member.exception.NotFoundLoginMemberException;
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

  public Member login(LoginRequest loginRequest) {
    Optional<Member> loginMember = memberRepository.findMemberForLogin(loginRequest.getSlackId(),
        loginRequest.getPassword());
    return loginMember.orElseThrow(() -> new NotFoundLoginMemberException());
  }

  @Transactional
  public MemberJoinResponse save(Member member) {
    Member saveMember = memberRepository.save(member);
    return new MemberJoinResponse(saveMember);
  }

  @Transactional
  public MemberJoinResponse approveJoin(TemporaryMember temporaryMember) {
    Member saveMember = memberRepository.save(temporaryMember.toMember());
    return new MemberJoinResponse(saveMember);
  }
}
