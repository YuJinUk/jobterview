package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.UpdatePasswordDto;

import java.util.Optional;

public interface MemberService {
    public Member save(Member member);

    public Member findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    public void quit(String email);

    public Page<Member> findByNicknameContains(Pageable pageable, String keyword);
    public Page<Member> findAll(Pageable pageable);

    Page<Member> findByEmailContains(Pageable pageable, String keyword);
    public void update(String email);
}
