package ssafy.project.jobterview.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Role;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.service.MemberService;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();    //google
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드" + uuid);  // 사용자가 입력한 적은 없지만 만들어준다
        String email = "";
        String name = "";
        Member byUsername = null;

        if (provider.equals("google")) {
            email = oAuth2User.getAttribute("email");

            try {
                byUsername = memberService.findByEmail(email);
            } catch (Exception e) {
                byUsername = null;
            }

            name = oAuth2User.getAttribute("name");

        } else if (provider.equals("kakao")) {
            // 카카오 응답에서 닉네임 추출
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            name = (String) profile.get("nickname");

            email = (String) kakaoAccount.get("email");
            try {
                byUsername = memberService.findByEmail(email);
            } catch (Exception e) {
                byUsername = null;
            }
        }

        //DB에 없는 사용자라면 회원가입처리
        if (byUsername == null) {
            Member newMember = new Member(email, name, password);
            newMember.changeRole(Role.ROLE_SOCIAL);
            memberRepository.save(newMember);
            return new PrincipalDetail(newMember, oAuth2User.getAttributes());
        } else {
            return new PrincipalDetail(byUsername, oAuth2User.getAttributes());
        }
    }
}
