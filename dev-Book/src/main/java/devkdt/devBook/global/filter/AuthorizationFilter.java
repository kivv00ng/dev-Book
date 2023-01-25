package devkdt.devBook.global.filter;

import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
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
public class AuthorizationFilter implements Filter {

  private static final String[] adminList = {"/books/update", "/books/delete", "/joinRequests"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    HttpServletResponse httpResponse = (HttpServletResponse) response;

    if (isAdminAuthorizationCheckPath(requestURI)) {
      //log.info("###AuthorizationFilterURI 확인 => {}", requestURI);
      HttpSession httpSession = httpRequest.getSession(false);
      Member loginMember = (Member) httpSession.getAttribute("loginMember");
      if (loginMember.getAuthority() != Authority.ADMIN) { //로그인 멤버의 권한이 admin이 아닐시 403 응답.
        //log.info("###AuthorizationFilter실패");
        httpResponse.setStatus(403);
        return;
      }
    }
    //log.info("###인가 필터 패스 또는 상관없는 URI");
    chain.doFilter(request, response);
  }

  private boolean isAdminAuthorizationCheckPath(String requestURI) {
    return PatternMatchUtils.simpleMatch(adminList, requestURI);
  }
}
