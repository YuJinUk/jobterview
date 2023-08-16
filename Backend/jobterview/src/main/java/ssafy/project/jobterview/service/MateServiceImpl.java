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


    /**
     * 메이트 추가
     * 
     * @param fromMemberNickname 메이트를 신청한 사용자 닉넹미
     * @param toMemberNickname 메이트를 신청당한 사용자 닉네임
     */
    public void save(String fromMemberNickname, String toMemberNickname) {
        //닉네임으로 해당 맴버 가져오기
        Member fromMember = memberService.findByNickname(fromMemberNickname);
        Member toMember = memberService.findByNickname(toMemberNickname);
        
        //메이트Id 생성
        MateId mateId = new MateId(fromMember.getMemberId(), toMember.getMemberId());
        
        //저장
        Mate savedMate = mateRepository.save(new Mate(mateId, fromMember, toMember));
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

    /**
     * 해당 사용자의 메이트 리스트 조회
     * 
     * @param pageable 페이징 정보 및 정렬 정보
     * @param nickname 사용자 닉네임
     * @return 페이징 처리된 해당 사용자의 메이트 리스트
     */
    @Override
    public Page<Mate> findAllByMate(Pageable pageable, String nickname) {
        Member findMember = memberService.findByNickname(nickname);
        return mateRepository.findByFromMember(findMember, pageable);
    }

    /**
     * 해당 사용자의 메이트 리스트 조회
     * 
     * @param nickname 사용자 닉네임
     * @return 해당 사용자의 메이트 리스트
     */
    @Override
    public List<Mate> findAllByMate(String nickname) {
        Member findMember = memberService.findByNickname(nickname);
        return mateRepository.findByFromMember(findMember);
    }

    @Override
    public Page<Mate> searchToMember(Pageable pageable, String fromMemberNickname, String keyword) {
        Member fromMember = memberService.findByNickname(fromMemberNickname);
        return mateRepository.findByFromMemberAndToMember_NicknameContaining(fromMember, keyword, pageable);
    }
}