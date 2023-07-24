package ssafy.project.jobterview.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 에러 메시지 생성
        String errorMessage = "아이디 또는 비밀번호가 올바르지 않습니다.";

        // 뷰에 전달할 데이터 생성
        Map<String, String> responseData = new HashMap<>();
        responseData.put("message", errorMessage);

        // 뷰로 데이터 전달
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(responseData);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonData);
    }


}