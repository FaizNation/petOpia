package FaizNation.petOpia_dev.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.lang.NonNull; 



@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // Allow access to login and registration pages
        String uri = request.getRequestURI();   
        if (uri.startsWith("/auth")) {
            return true;
        }

        // Check if user is logged in
        Object userSession = request.getSession().getAttribute("loggedInUser");
        if (userSession == null) {
            response.sendRedirect("/auth/login");
            return false;
        }

        return true;
    }
}
