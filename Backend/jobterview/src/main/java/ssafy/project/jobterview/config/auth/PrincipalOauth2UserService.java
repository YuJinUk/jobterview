package ssafy.project.jobterview.config.auth;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> dev
import lombok.RequiredArgsConstructor;
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
import ssafy.project.jobterview.service.MemberService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private MemberRepository memberRepository;
    private MemberService memberService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PrincipalOauth2UserService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MemberService memeberService) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberService = memeberService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();    //google

        //String providerId = oAuth2User.getAttribute("sub");
        //String username = provider+"_"+providerId;              // 사용자가 입력한 적은 없지만 만들어준다


        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);  // 사용자가 입력한 적은 없지만 만들어준다
        String email = "";
        String name = "";
//        Optional<Member> byUsername = Optional.empty();
        Member byUsername = null;




        if(provider.equals("google")){

            email = oAuth2User.getAttribute("email");

            try {
                byUsername = memberService.findByEmail(email);
            } catch (Exception e) {
                byUsername =null;

            }

            name = oAuth2User.getAttribute("name");

        }else if(provider.equals("kakao")){
            System.out.println("오긴함?");
            // 카카오 응답에서 닉네임 추출
            Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            name = (String) profile.get("nickname");
            name = name+"10";
            email = (String) kakaoAccount.get("email");

            // 가져온 닉네임을 출력하거나 필요한 로직으로 사용합니다.
            //System.out.println("닉네임: " + nickname);
        }



        //DB에 없는 사용자라면 회원가입처리
        if(byUsername==null){
            Member newMember = new Member(email,name,password);

            newMember.setRole(Role.ROLE_user);
            memberRepository.save(newMember);
            return new PrincipalDetail(newMember, oAuth2User.getAttributes());
        }else{
            return new PrincipalDetail(byUsername, oAuth2User.getAttributes());

        }

    }
}
