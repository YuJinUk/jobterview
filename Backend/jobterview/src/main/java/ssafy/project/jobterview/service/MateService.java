package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;

import java.util.List;

public interface MateService {
    // 메이트 신청
    void save(String fromMemberNickname, String toMemberNickname);

    // 메이트 끊기
    void delete(String fromMemberNickname, String toMemberNickname);
    
    //페이징 처리된 메이트 찾기
    Page<Mate> findAllByMate(Pageable pageable, String nickname);

    List<Mate> findAllByMate(String nickname);

    Page<Mate> searchToMember(Pageable pageable, String fromMemberNickname, String keyword);

}