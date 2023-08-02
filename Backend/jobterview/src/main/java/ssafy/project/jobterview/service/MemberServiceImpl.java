package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Role;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.MemberRepository;

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

    @Override
    public void emailAuth(String email) {
        Member member = findByEmail(email);
        member.setRole(Role.ROLE_user);
        memberRepository.save(member);
    }

    @Override
    public Long getAllActiveMemberCount() {
        return memberRepository.countByIsActiveTrue();
    }
}
