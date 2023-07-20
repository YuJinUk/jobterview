package ssafy.project.jobterview.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.repository.MemberRepository;




@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private  MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member principal = memberRepository.findByEmail(email);

        if(principal == null){
            System.out.println("이메일 없음");
            return null;

        }

        return new PrincipalDetail(principal);
    }
}
