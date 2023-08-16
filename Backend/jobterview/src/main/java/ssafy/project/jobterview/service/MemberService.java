package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MemberDto;

public interface MemberService {
    Member save(Member member);

    Member findByEmail(String email);

    Member findByNickname(String nickname);

    Member findById(Long id);

    void quit(String email);

    Page<Member> findByNicknameContains(Pageable pageable, String keyword);

    Page<Member> findByNicknameContainsAndRole(Pageable pageable, String keyword);

    Page<Member> findByEmailContains(Pageable pageable, String keyword);

    Page<Member> findAll(Pageable pageable);

    void update(String email);
    
    void reJoin(MemberDto memberDto);

    Long getAllActiveMemberCount();

    Page<Member> getAllActiveMember(Pageable pageable);

    void emailAuth(String email, String code);

    void setEmailCode(String email, String code);

    void passwordAuth(String email, String code, String password);
}
