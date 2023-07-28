package ssafy.project.jobterview.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.service.MemberService;


@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private  MemberRepository memberRepository;
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member principal = memberService.findByEmail(email);

        if(principal == null){
            System.out.println("이메일 없음");
            return null;

        }

        return new PrincipalDetail(principal);
    }
}
