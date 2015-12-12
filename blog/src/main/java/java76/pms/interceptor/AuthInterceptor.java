package java76.pms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java76.pms.domain.Member;
import java76.pms.filter.AuthFilter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
  private static final Logger log = Logger.getLogger(AuthFilter.class);
  
  @Override
  public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler) throws Exception {
    
    log.debug("로그인 인터셉터 실행!");
    
    Member loginUser = (Member)request.getSession()
                                        .getAttribute("loginUser");
    
    if (!request.getServletPath().startsWith("/main") 
    		&& !request.getServletPath().startsWith("/auth")
    		&& !request.getServletPath().startsWith("/member/reg")
        && loginUser == null) {
      response.sendRedirect(request.getContextPath() + "/main/first.do");
      log.debug("여기서 멈춰라! servletpath=>" + request.getServletPath() );
      return false; // 다음으로 가는 것을 멈춰라!
    }
    log.debug("실행해라! servletpath=>" + request.getServletPath());
    return true; // 다음 인터셉터나 페이지 컨트롤러로 가라.
  }
}
