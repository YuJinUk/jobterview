package ssafy.project.jobterview.service;

import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.UpdatePasswordDto;

import java.util.Optional;

public interface MemberService {
    public Member save(Member member);

    public Member findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    public void quit(String email);
}
