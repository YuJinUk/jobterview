package ssafy.project.jobterview.config.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ssafy.project.jobterview.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetail implements UserDetails, OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;

    public PrincipalDetail(Member member) {
        this.member = member;
    }

    //OAuth2User : OAuth2 로그인 시 사용
    public PrincipalDetail(Member member, Map<String, Object> attributes) {
        //PrincipalOauth2UserService 참고
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole().getDescription();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        try {
            return member.getPassword();
        } catch (Exception e) {
            return "123";
        }
    }

    @Override
    public String getUsername() {
        try {
            return member.getEmail();
        } catch (Exception e) {
            return "123";
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * OAuth2User 구현
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * OAuth2User 구현
     */
    @Override
    public String getName() {
        return attributes.get("sub").toString();
    }
}
