package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.MateId;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.MateRepository;
import ssafy.project.jobterview.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateServiceImpl implements MateService {

    private final MateRepository mateRepository;
    private final MemberService memberService;

    // 메이트 추가
    @Override
    public void save(String fromMemberNickname, String toMemberNickname) {
        //닉네임으로 해당 맴버 가져오기
        Member fromMember = memberService.findByNickname(fromMemberNickname);
        Member toMember = memberService.findByNickname(toMemberNickname);
        
        //메이트Id 생성
        MateId mateId = new MateId(fromMember.getMemberId(), toMember.getMemberId());
        
        //저장
        mateRepository.save(new Mate(mateId, fromMember, toMember));
    }

    // 메이트 삭제
    @Override
    public void delete(String fromMemberNickname, String toMemberNickname) {
        //닉네임으로 해당 맴버 가져오기
        Member fromMember = memberService.findByNickname(fromMemberNickname);
        Member toMember = memberService.findByNickname(toMemberNickname);

        Mate findMate = mateRepository.findByFromMemberAndToMember(fromMember, toMember)
                .orElseThrow(() -> new NotFoundException("해당 메이트를 찾기 못했습니다."));
        mateRepository.delete(findMate);
    }

    @Override
    public Page<Mate> findAllByMate(Pageable pageable, String nickname) {
        Member findMember = memberService.findByNickname(nickname);

        System.out.println("findMember ToString : " + findMember.toString());

        Page<Mate> matePage = mateRepository.findByFromMember(findMember, pageable);

        System.out.println("pagedMate size : " + matePage.getContent().size());

        return matePage;
    }

    @Override
    public List<Mate> findByFromMember(Member fromMember) {
        List<Mate> findMateList = mateRepository.findByFromMember(fromMember);
        if(findMateList.size() == 0) {
            throw new NotFoundException("메이트가 없습니다.");
        }
        return findMateList;
    }

    @Override
    public Page<Mate> searchToMember(Pageable pageable, String fromMemberNickname, String keyword) {
        Member fromMember = memberService.findByNickname(fromMemberNickname);
        return mateRepository.findByFromMemberAndToMember_NicknameContaining(fromMember, keyword, pageable);
    }

}