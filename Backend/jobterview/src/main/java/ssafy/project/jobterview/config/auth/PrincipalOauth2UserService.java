package ssafy.project.jobterview.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Role;
import ssafy.project.jobterview.repository.MemberRepository;

import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PrincipalOauth2UserService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();    //google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider+"_"+providerId;  			// 사용자가 입력한 적은 없지만 만들어준다

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);  // 사용자가 입력한 적은 없지만 만들어준다

        String email = oAuth2User.getAttribute("email");
        Role role = Role.user;

        Member byUsername = memberRepository.findByEmail(email).orElseThrow();
        System.out.println(username);
        System.out.println(role);
        System.out.println(email);

        //DB에 없는 사용자라면 회원가입처리
        if(byUsername == null){
            byUsername.setEmail(email);

            memberRepository.save(byUsername);
        }

        return new PrincipalDetail(byUsername, oAuth2User.getAttributes());
    }
}
