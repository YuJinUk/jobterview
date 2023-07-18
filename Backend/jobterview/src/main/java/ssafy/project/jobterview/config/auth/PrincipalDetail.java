package ssafy.project.jobterview.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ssafy.project.jobterview.domain.Member;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetail implements UserDetails {

    private Member member; //콤포지션

    public PrincipalDetail(Member member){
        this.member = member;
    }

    @Override //해당 유저의 권한 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collet = new ArrayList<>();
//
//        public String getAuthority(){
//            return member.getRole;
//        }
        //return collect;
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
