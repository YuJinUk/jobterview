package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("일치하는 회원이 존재하지 않습니다."));
    }

    @Override
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("일치하는 회원이 존재하지 않습니다."));
    }

    @Override
    public void quit(String email) {
        Member member = this.findByEmail(email);
        member.setIsActive(0); // 탈퇴된 상태로 변경
        memberRepository.save(member);
    }

    @Override
    public Page<Member> findByNicknameContains(Pageable pageable, String keyword) {
        return memberRepository.findByNicknameContains(pageable, keyword);
    }
}
