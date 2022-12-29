package devkdt.devBook.config;

import devkdt.devBook.entity.Authority;
import devkdt.devBook.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class AuthorizationFilter implements Filter {
    private static final String[] adminList = {"/books/update", "/books/delete", "/joinRequests"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isAdminAuthorizationCheckPath(requestURI)) {
            log.info("###AuthorizationFilterURI 확인 => {}", requestURI);
            HttpSession httpSession = httpRequest.getSession(false);
            if (httpSession == null) {

            }
            Member loginMember = (Member) httpSession.getAttribute("loginUser");
            if (loginMember.getAuthority() != Authority.ADMIN) { //로그인 멤버의 권한이 admin이 아닐시 403 응답.
                log.info("###AuthorizationFilter실패");
                httpResponse.setStatus(403);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isAdminAuthorizationCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(adminList, requestURI);
    }
}
