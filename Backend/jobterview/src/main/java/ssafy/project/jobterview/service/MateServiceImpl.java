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
    public void save(MateDto mateDto){
        Member fromMember = mateDto.getFromMember();
        Member toMember= mateDto.getToMember();
        Mate mate = new Mate(mateDto.getMateId(),fromMember,toMember);
        mateRepository.save(mate);
    }


    public MateDto save(String fromMemberNickname, String toMemberNickname) {
        //닉네임으로 해당 맴버 가져오기
        Member fromMember = memberService.findByNickname(fromMemberNickname);
        Member toMember = memberService.findByNickname(toMemberNickname);
        
        //메이트Id 생성
        MateId mateId = new MateId(fromMember.getMemberId(), toMember.getMemberId());
        
        //저장
        Mate savedMate = mateRepository.save(new Mate(mateId, fromMember, toMember));
        
        //저장된 Mate를 MateDto로 변환 후 반환
        return savedMate.convertToDto();
    }

    // 메이트 삭제
    @Override
    public void delete(MateDto mateDto) {
        Mate mate = new Mate(mateDto.getMateId(), mateDto.getFromMember(), mateDto.getToMember());
        mateRepository.delete(mate);
    }

    // 메이트 조회
    @Override
    public Page<Mate> findAllByMate(Pageable pageable, String nickname){
        return mateRepository.findAllByFromMember(pageable, nickname);
    }

    @Override
    public List<Mate> findByFromMember(Member fromMember) {
        List<Mate> findMateList = mateRepository.findByFromMember(fromMember);
        if(findMateList.size() == 0) {
            throw new NotFoundException("메이트가 없습니다.");
        }
        return findMateList;
    }
}