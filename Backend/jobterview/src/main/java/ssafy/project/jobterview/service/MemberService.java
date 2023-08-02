package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;

public interface MemberService {
    Member save(Member member);

    Member findByEmail(String email);

    Member findByNickname(String nickname);

    void quit(String email);

    public Page<Member> findByNicknameContains(Pageable pageable, String keyword);

    public Page<Member> findByEmailContains(Pageable pageable, String keyword);

    public Page<Member> findAll(Pageable pageable);

    public void update(String email);

    public void emailAuth(String email);

}
