package ssafy.project.jobterview.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.service.MemberService;


@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Member principal = memberService.findByEmail(email);
            if (principal.getRole().toString().equals("ROLE_WITHDRAWN")) {
                principal = null;
            }
            return new PrincipalDetail(principal);
        } catch (Exception e) {
            Member principal = null;
            return new PrincipalDetail(principal);
        }
    }
}

