package devkdt.devBook.controller;

import devkdt.devBook.dto.LoginRequest;
import devkdt.devBook.dto.MemberJoinRequest;
import devkdt.devBook.dto.MemberJoinResponse;
import devkdt.devBook.entity.Member;
import devkdt.devBook.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        try {
            LoginRequest loginRequest = httpEntity.getBody();
            Member loginMember = memberService.login(loginRequest);

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("loginMember", loginMember);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.info("#### Member Not Found Exception");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/test/session")
    public ResponseEntity<Object> getCookie(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        if (httpSession == null) {
            log.info("### login fail!");
            return ResponseEntity.badRequest().build();
        }
        Member checkMember = (Member) httpSession.getAttribute("loginMember");
        log.info("#### loginMember = {}", checkMember);
        return ResponseEntity.ok(checkMember);

    }

    @PostMapping("/members/add")
    public ResponseEntity<MemberJoinResponse> addMember(HttpEntity<MemberJoinRequest> httpEntity) {
        MemberJoinRequest memberJoinRequest = httpEntity.getBody();
        MemberJoinResponse memberJoinResponse = memberService.save(memberJoinRequest.toMember());
        log.info("###memberJoinResponse={}", memberJoinResponse);
        return ResponseEntity.ok(memberJoinResponse);

    }

}
