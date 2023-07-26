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

    @Override
    public Page<Member> findByNicknameContains(Pageable pageable, String keyword) {
        return memberRepository.findByNicknameContains(pageable, keyword);
    }
<<<<<<< HEAD
=======
    @Override
    public Page<Member> findByEmailContains(Pageable pageable, String keyword) {
        return memberRepository.findByEmailContains(pageable, keyword);
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public void update(String email) {
        Member member = findByEmail(email);
        int a = member.getIsActive();
        if(a==1){
            member.setIsActive(2);
        }
        else if(a==2){
            member.setIsActive(1);
        }
        memberRepository.save(member);

    }
>>>>>>> 4cfa8b4 (관리자, 메이트, 쪽지 완성)
}
