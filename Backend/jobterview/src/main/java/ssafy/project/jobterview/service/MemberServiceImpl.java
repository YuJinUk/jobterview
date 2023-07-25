package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.UpdatePasswordDto;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Override
    public void quit(String email) {
        Member member = memberRepository.findByEmail(email);
        member.setIsActive(0); // 탈퇴된 상태로 변경
        memberRepository.save(member);
    }
}
