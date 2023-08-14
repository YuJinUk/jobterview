package ssafy.project.jobterview.config.auth;
;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.service.MemberService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
public class SocialAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;
    private final Environment env;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 인증 정보로부터 사용자 정보 추출
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username2 = userDetails.getUsername();
        String usernickname = "";
        String userRole = "";
        try {
            Member loginMember = memberService.findByEmail(username2);
            usernickname = loginMember.getNickname();
            userRole = loginMember.getRole().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encodedNickname = URLEncoder.encode(usernickname, StandardCharsets.UTF_8);
        if(userRole.toString().equals("ROLE_REPORTED_SOCIAL")){
            setDefaultTargetUrl(env.getProperty("varialbles.feUri")+"/auth/login?report=ok");
        }else{
            setDefaultTargetUrl(env.getProperty("varialbles.feUri")+"?nickname=" + encodedNickname+"&role="+userRole);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }


}
