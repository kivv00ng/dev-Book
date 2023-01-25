package devkdt.devBook.global.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

  private static final String[] whitelist = {"/", "/members/add", "/api/login", "/logout",
      "/css/*"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    HttpServletResponse httpResponse = (HttpServletResponse) response;

    //log.info("####인증필터 시작 {}", requestURI);

    if (isLoginCheckPath(requestURI)) {
      //log.info("인증 체크 로직 실행 {}", requestURI);
      HttpSession httpSession = httpRequest.getSession(false);
      if (httpSession == null || httpSession.getAttribute("loginMember") == null) {
        //log.info("### 미인증 사용자 요청 {}", requestURI);

        //로그인 redirect
        httpResponse.setStatus(401);
        //or
        //httpResponse.sendRedirect("/?redirectURL=" + requestURI);
        return; //미인증 사용자는 다음으로 진행하지 않고 끝!
      }
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
