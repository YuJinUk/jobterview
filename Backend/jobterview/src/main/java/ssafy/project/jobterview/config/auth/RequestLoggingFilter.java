package ssafy.project.jobterview.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURL = request.getRequestURL().toString();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();

        // 로깅 작업 수행 (예: 로깅 라이브러리 사용)
        log.info("Incoming request - Method: " + method + ", URL: " + requestURL + ", Remote Addr: " + remoteAddr);

        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);

        } catch (Exception e){
            log.error(e.getMessage());
        } finally {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            log.info("URL: " + requestURL + ", elapsed time: " + elapsedTime + "ms");
        }
    }
}
