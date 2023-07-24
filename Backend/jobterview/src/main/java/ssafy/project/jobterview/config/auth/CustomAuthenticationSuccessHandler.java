package ssafy.project.jobterview.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 인증 정보로부터 사용자 정보 추출
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println((PrincipalDetail)userDetails.getNickname());
        String username = userDetails.getUsername();


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 뷰에 전달할 데이터 생성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("email", username);
        responseData.put("roles", roles);
        responseData.put("message", "로그인 성공");

        // 뷰로 데이터 전달
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(responseData);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonData);
    }


}
