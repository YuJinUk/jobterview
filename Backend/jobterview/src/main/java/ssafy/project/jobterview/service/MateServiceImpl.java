package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;
import ssafy.project.jobterview.repository.MateRepository;
import ssafy.project.jobterview.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MateServiceImpl implements MateService {

    private final MateRepository mateRepository;

    // 메이트 추가
    @Override
    public void save(MateDto mateDto){
        Member fromMember = mateDto.getFromMember();
        Member toMember= mateDto.getToMember();
        Mate mate = new Mate(mateDto.getMateId(),fromMember,toMember);
        mateRepository.save(mate);
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


}
