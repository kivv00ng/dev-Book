package devkdt.devBook.member.presentation;

import devkdt.devBook.member.application.MemberService;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.dto.LoginRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/api/login")
  public ResponseEntity<Void> login(HttpEntity<LoginRequest> httpEntity,
      HttpServletRequest request) {
    LoginRequest loginRequest = httpEntity.getBody();
    Member loginMember = memberService.login(loginRequest);
    HttpSession httpSession = request.getSession();
    httpSession.setAttribute("loginMember", loginMember);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/api/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request) {
    HttpSession httpSession = request.getSession(false);

    if (httpSession != null) {
      httpSession.invalidate();
    }

    return ResponseEntity.ok().build();
  }
}
