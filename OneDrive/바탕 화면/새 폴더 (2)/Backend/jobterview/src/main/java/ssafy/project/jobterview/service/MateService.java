package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;

import java.util.List;

public interface MateService {
    // 메이트 신청
    void save(MateDto mateDto);

    MateDto save(String fromMemberNickname, String toMemberNickname);

    // 메이트 끊기
    void delete(MateDto mateDto);
    
    //페이징 처리된 메이트 찾기
    Page<Mate> findAllByMate(Pageable pageable, String nickname);

    //메이트 찾기
    List<Mate> findByFromMember(Member fromMember);
}
