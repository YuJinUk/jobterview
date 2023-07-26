package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.Member;

public interface MateRepository extends JpaRepository<Mate, Member>, MateCustomRepository{


}
