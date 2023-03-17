package devkdt.devBook.joinRequest.presentation;

import devkdt.devBook.joinRequest.application.JoinRequestService;
import devkdt.devBook.joinRequest.domain.JoinRequest;
import devkdt.devBook.joinRequest.dto.JoinManagementResponse;
import devkdt.devBook.joinRequest.dto.MemberApplyResponse;
import devkdt.devBook.joinRequest.dto.MemberJoinRequest;
import devkdt.devBook.joinRequest.dto.TemporaryMemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JoinController {

  private JoinRequestService joinRequestService;

  public JoinController(JoinRequestService joinRequestService) {
    this.joinRequestService = joinRequestService;
  }

  @PostMapping("/api/join")
  public ResponseEntity<TemporaryMemberResponse> addJoinRequest(
      @RequestBody MemberJoinRequest memberJoinRequest) {
    JoinRequest joinRequest = joinRequestService.save(memberJoinRequest.toTemporaryMember());
    log.info("join 요청!");
    return ResponseEntity.ok(new TemporaryMemberResponse(joinRequest.getTemporaryMember()));
  }

  @GetMapping("/api/joinRequest/all")
  public ResponseEntity<JoinManagementResponse> findAll() {
    JoinManagementResponse joinManagementResponse = joinRequestService.findAll();
    return ResponseEntity.ok(joinManagementResponse);
  }

  @PostMapping("/api/joinRequest/apply/{id}")
  public ResponseEntity<MemberApplyResponse> applyJoin(@PathVariable("id") long id) {
    MemberApplyResponse memberApplyResponse = joinRequestService.apply(id);
    log.info("join apply!");
    return ResponseEntity.ok(memberApplyResponse);
  }

  @DeleteMapping("/api/joinRequest/delete/{id}")
  public ResponseEntity<Void> deleteJoin(@PathVariable("id") long id) {
    joinRequestService.delete(id);
    log.info("join delete!");
    return ResponseEntity.ok().build();
  }

}
