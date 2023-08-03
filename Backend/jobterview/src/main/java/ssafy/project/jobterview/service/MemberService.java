package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;

public interface MemberService {
    Member save(Member member);

    Member findByEmail(String email);

    Member findByNickname(String nickname);

    void quit(String email);

    Page<Member> findByNicknameContains(Pageable pageable, String keyword);

    Page<Member> findByEmailContains(Pageable pageable, String keyword);

    Page<Member> findAll(Pageable pageable);

    void update(String email);
    
    Long getAllActiveMemberCount();

    public void emailAuth(String email);

}
