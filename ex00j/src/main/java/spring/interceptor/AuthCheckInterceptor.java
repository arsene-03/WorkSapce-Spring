package spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.vo.AuthInfo;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{//implements HandlerInterceptor{// 로그인을 감지하기 위한 인터셉터 클래스
	
	// 어댑터 클래스 => 인터페이스를 직접 구현하지 않고 어댑터클래스가 인터페이스를 구현해서 
	// 추상메서드를 오버라이딩해서 몸체는 있지만 아무 내용이 없는 메서드를 만들어 두고 
	// 상속받은 클래스가 필요한 기능만 구현하도록 도와주는 클래스
	
	@Override   // 컨트롤러(핸들러) 실행되기 전  => 로그인 검증
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session != null) {
			// 로그인이 되어 있는지 검증하는 코드를 작성
			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
			
			if(authInfo!=null) {//로그인이 되어 있음
				return true;
			}
		}
		 response.sendRedirect(request.getContextPath()+"/login");
		return false;
	}
//
//	@Override  // 컨트롤러(핸들러)가 실행된 후, 그리고 뷰가 열리기 전
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//	}
//
//	@Override // 뷰가 열린 후
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	}

	
	
}
