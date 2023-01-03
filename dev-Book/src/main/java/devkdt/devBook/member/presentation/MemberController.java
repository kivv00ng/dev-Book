package devkdt.devBook.member.presentation;

import devkdt.devBook.member.dto.LoginRequest;
import devkdt.devBook.joinRequest.dto.MemberJoinRequest;
import devkdt.devBook.joinRequest.dto.MemberJoinResponse;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(HttpEntity<LoginRequest> httpEntity, HttpServletRequest request, HttpServletResponse response) {
        //아무래도 예외처리전에, postMan으로 확인하는 작업에서 잘 작동하는지 로그보며 확인하려고 작성한 tryCatch인 듯하다.
//        try {
//            LoginRequest loginRequest = httpEntity.getBody();
//            Member loginMember = memberService.login(loginRequest);
//
//            HttpSession httpSession = request.getSession();
//            httpSession.setAttribute("loginMember", loginMember);
//            return ResponseEntity.ok().build();
//        } catch (RuntimeException e) {
//            log.info("#### Member Not Found Exception");
//            return ResponseEntity.badRequest().build();
//        }
        LoginRequest loginRequest = httpEntity.getBody();
        Member loginMember = memberService.login(loginRequest);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("loginMember", loginMember);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/members/add")
    public ResponseEntity<MemberJoinResponse> addMember(HttpEntity<MemberJoinRequest> httpEntity) {
        MemberJoinRequest memberJoinRequest = httpEntity.getBody();
        MemberJoinResponse memberJoinResponse = memberService.save(memberJoinRequest.toMember());
        //log.info("###memberJoinResponse={}", memberJoinResponse);
        return ResponseEntity.ok(memberJoinResponse);

    }

    //session을 확인하는 Controller
//    @GetMapping("/test/session")
//    public ResponseEntity<Object> getCookie(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession(false);
//
//        if (httpSession == null) {
//            log.info("### login fail!");
//            return ResponseEntity.badRequest().build();
//        }
//        Member checkMember = (Member) httpSession.getAttribute("loginMember");
//        log.info("#### loginMember = {}", checkMember);
//        return ResponseEntity.ok(checkMember);
//
//    }

}
