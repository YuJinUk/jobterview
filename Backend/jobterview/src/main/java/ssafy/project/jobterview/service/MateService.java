package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.MateId;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;

public interface MateService {
    // 메이트 신청
    void save(MateDto mateDto);

    void delete(MateDto mateDto);


    Page<Mate> findAllByMate(Pageable pageable, String nickname);



}
