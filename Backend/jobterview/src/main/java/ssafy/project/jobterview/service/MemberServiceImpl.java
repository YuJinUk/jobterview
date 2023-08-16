package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Role;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

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
    public Page<Member> findByNicknameContainsAndRole(Pageable pageable, String keyword) {
        Page<Member> socialPage= memberRepository.findByNicknameContainsAndRole(pageable, keyword, Role.ROLE_SOCIAL);
        Page<Member> localPage = memberRepository.findByNicknameContainsAndRole(pageable,keyword,Role.ROLE_LOCAL);

        List<Member> page = new ArrayList<>(localPage.getContent());

        page.addAll(socialPage.getContent());

        return new PageImpl<>(page, pageable, page.size());
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
        // 로컬로 가입한 유저 정지
        if(memberRole == Role.ROLE_LOCAL){
            member.changeRole(Role.ROLE_REPORTED_LOCAL);
        }
        // 소셜로 가입한 유저 정지
        else if(memberRole == Role.ROLE_SOCIAL){
            member.changeRole(Role.ROLE_REPORTED_SOCIAL);
        }
        // 로컬로 가입한 유저 정지 풀기
        else if(memberRole == Role.ROLE_REPORTED_LOCAL){
            member.changeRole(Role.ROLE_LOCAL);
        }
        // 소셜로 가입한 유저 정지 풀기
        else if(memberRole == Role.ROLE_REPORTED_SOCIAL){
            member.changeRole(Role.ROLE_SOCIAL);
        }
        memberRepository.save(member);
    }


    @Override
    public void reJoin(MemberDto memberDto) {
        Member member = findByEmail(memberDto.getEmail());
        member.changeRole(Role.ROLE_UNVERIFIED);
        member.insertPassword(member.getPassword());
        member.changeNickname(member.getNickname());
        memberRepository.save(member);
    }
    @Override
    public void emailAuth(String email, String code) {
        Member member = findByEmail(email);
        // 링크의 인증번호와 DB의 인증 번호가 같으면 인증 완료로 변경
        if(member.getAuthCode().equals(code)) {
            member.changeRole(Role.ROLE_LOCAL);
            memberRepository.save(member);
        } else {
            throw new NotFoundException("인증 번호가 일치하지 않습니다.");
        }

    }

    @Override
    public void passwordAuth(String email, String code, String password) {
        Member member = findByEmail(email);
        // 링크의 인증번호와 DB의 인증 번호가 같으면 인증 완료로 변경
        if(member.getAuthCode().equals(code)) {
            member.insertPassword(password);
            memberRepository.save(member);
        } else {
            throw new NotFoundException("인증 번호가 일치하지 않습니다.");
        }

    }

    @Override
    public Long getAllActiveMemberCount() {
        Long count1 = memberRepository.countByRole(Role.ROLE_SOCIAL);
        Long count2 = memberRepository.countByRole(Role.ROLE_LOCAL);
        return count1+count2;
    }

    @Override
    public Page<Member> getAllActiveMember(Pageable pageable) {
        Page<Member> localPage = memberRepository.findByRole(Role.ROLE_LOCAL,pageable);
        Page<Member> socialPage = memberRepository.findByRole(Role.ROLE_SOCIAL,pageable);
        List<Member> page = new ArrayList<>(localPage.getContent());
        page.addAll(socialPage.getContent());
        return new PageImpl<>(
                page,
                pageable,
                page.size()
        );}

    @Override
    public void setEmailCode(String email, String code) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("일치하는 회원이 존재하지 않습니다."));
        member.setAuthCode(code);
        memberRepository.save(member);
    }
}
