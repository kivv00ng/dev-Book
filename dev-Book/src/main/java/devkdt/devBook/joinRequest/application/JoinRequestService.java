package devkdt.devBook.joinRequest.application;

import devkdt.devBook.joinRequest.domain.JoinRequest;
import devkdt.devBook.joinRequest.domain.JoinRequestRepository;
import devkdt.devBook.joinRequest.domain.TemporaryMember;
import devkdt.devBook.joinRequest.dto.JoinManagementResponse;
import devkdt.devBook.joinRequest.dto.JoinOneResponse;
import devkdt.devBook.joinRequest.dto.MemberApplyResponse;
import devkdt.devBook.joinRequest.exception.JoinRequestNotFoundException;
import devkdt.devBook.member.application.MemberService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class JoinRequestService {

  private final JoinRequestRepository joinRequestRepository;
  private final MemberService memberService;
  private final SlackService slackService;

  public JoinRequestService(JoinRequestRepository joinRequestRepository,
      MemberService memberService,
      SlackService slackService) {
    this.joinRequestRepository = joinRequestRepository;
    this.memberService = memberService;
    this.slackService = slackService;
  }

  @Transactional
  public JoinRequest save(TemporaryMember temporaryMember) {
    JoinRequest joinRequest = joinRequestRepository.save(new JoinRequest(temporaryMember));
    slackService.postSlackMessage(
        temporaryMember.getName() + "(" + temporaryMember.getSlackId() + ")님이회원가입을 요청하셨습니다. : "
            + temporaryMember);

    return joinRequest;
  }

  @Transactional
  public void delete(Long joinId) {
    joinRequestRepository.deleteById(joinId);
  }

  public JoinManagementResponse findAll() {
    List<JoinRequest> joinRequests = joinRequestRepository.findAll();

    List<JoinOneResponse> joinOneResponses = joinRequests.stream()
        .map(joinRequest -> new JoinOneResponse(joinRequest)).collect(Collectors.toList());

    return new JoinManagementResponse(joinOneResponses);
  }

  @Transactional
  public MemberApplyResponse apply(Long joinRequestId) {
    JoinRequest joinRequest = joinRequestRepository.findById(joinRequestId).orElseThrow(() -> {
      throw new JoinRequestNotFoundException(joinRequestId);
    });

    MemberApplyResponse memberApplyResponse = memberService.approveJoin(
        joinRequest.getTemporaryMember());

    joinRequestRepository.deleteById(joinRequestId);

    return memberApplyResponse;
  }
}
