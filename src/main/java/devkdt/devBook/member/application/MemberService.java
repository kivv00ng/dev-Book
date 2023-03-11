package devkdt.devBook.member.application;

import devkdt.devBook.joinRequest.domain.TemporaryMember;
import devkdt.devBook.joinRequest.dto.MemberApplyResponse;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import devkdt.devBook.member.dto.LoginRequest;
import devkdt.devBook.member.exception.NotFoundLoginMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public Boolean existsByPhoneNumber(String phoneNumber) {
    return memberRepository.existsByPhoneNumber(phoneNumber);
  }

  public Member login(LoginRequest loginRequest) {
    Member loginMember = memberRepository.findMemberForLogin(loginRequest.getSlackId(),
        loginRequest.getPassword()).orElseThrow(() -> {
      throw new NotFoundLoginMemberException();
    });

    return loginMember;
  }

  @Transactional
  public MemberApplyResponse save(Member member) {
    Member saveMember = memberRepository.save(member);
    return new MemberApplyResponse(saveMember);
  }

  @Transactional
  public MemberApplyResponse approveJoin(TemporaryMember temporaryMember) {
    Member saveMember = memberRepository.save(temporaryMember.toMember());
    return new MemberApplyResponse(saveMember);
  }

}
