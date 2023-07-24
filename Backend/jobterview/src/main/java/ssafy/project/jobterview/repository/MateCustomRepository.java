package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;

public interface MateCustomRepository {
    public Page<Mate> findAllByFromMember(Pageable pageable, String nickname);


}
