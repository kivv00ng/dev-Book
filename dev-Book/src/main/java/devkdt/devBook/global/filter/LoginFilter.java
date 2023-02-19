package devkdt.devBook.global.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginFilter implements Filter {

  private static final String[] whitelist = {"/", "/page", "/books/*", "/api/books/detail/*",
      "/images/*", "/newMember", "/members/add", "/api/join", "/api/login", "/login", "/logout",
      "/css/*",
      "/favicon.ico"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    HttpServletResponse httpResponse = (HttpServletResponse) response;

//    log.info("####인증필터 시작 {}", requestURI);

    if (isLoginCheckPath(requestURI)) {
      log.info("인증 체크 로직 실행 {}", requestURI);
      HttpSession httpSession = httpRequest.getSession(false);
      if (httpSession == null) {
        log.info("### 미인증 사용자, httpSession Null 요청{}", requestURI);

        httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
        return; //미인증 사용자는 다음으로 진행하지 않고 끝!
      }
      if (httpSession.getAttribute("loginMember") == null) {
        log.info("### 미인증 사용자, 해당 쿠키에 해당하는 session은 있지만, loginMember라는 키값에 해당하는 valu는 없는 요청 {}",
            requestURI);

        httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
        return; //미인증 사용자는 다음으로 진행하지 않고 끝!
      }
//      if (httpSession == null || httpSession.getAttribute("loginMember") == null) {
//        log.info("### 미인증 사용자 요청 {}", requestURI);
//
//        //로그인 redirect
////        httpResponse.setStatus(401);
////        or
//        httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
//        return; //미인증 사용자는 다음으로 진행하지 않고 끝!
//      }
    }
    chain.doFilter(request, response);
  }

  /**
   * 화이트 리스트의 경우 인증체크X
   *
   * @param requestURI
   * @return boolean
   */

  private boolean isLoginCheckPath(String requestURI) {
    return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }
}
