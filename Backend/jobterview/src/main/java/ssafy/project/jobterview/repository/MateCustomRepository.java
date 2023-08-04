package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Mate;

public interface MateCustomRepository {
    Page<Mate> findAllByFromMember(Pageable pageable, String nickname);


}
