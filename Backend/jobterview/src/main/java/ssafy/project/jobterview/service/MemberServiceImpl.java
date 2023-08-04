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
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("일치하는 회원이 존재하지 않습니다."));
    }

    @Override
    public void quit(String email) {
        Member member = this.findByEmail(email);
        member.changeRole(Role.ROLE_WITHDRAWN); // 탈퇴된 상태로 변경
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
        Role memberRole = member.getRole();
        if(memberRole == Role.ROLE_MEMBER){
            member.changeRole(Role.ROLE_WITHDRAWN);
        }
        else if(memberRole == Role.ROLE_WITHDRAWN){
            member.changeRole(Role.ROLE_MEMBER);
        }
        memberRepository.save(member);
    }

    @Override
    public void emailAuth(String email) {
        Member member = findByEmail(email);
        member.changeRole(Role.ROLE_MEMBER);
        memberRepository.save(member);
    }

    @Override
    public Long getAllActiveMemberCount() {
        Long count = memberRepository.countByRole(Role.ROLE_MEMBER);
        return count;
    }

    @Override
    public Page<Member> getAllActiveMember(Pageable pageable) {
        return memberRepository.findByRole(Role.ROLE_MEMBER, pageable);
    }
}
