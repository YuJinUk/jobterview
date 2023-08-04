package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.Member;

import java.util.List;

public interface MateRepository extends JpaRepository<Mate, Member>, MateCustomRepository {

    List<Mate> findByFromMember(Member fromMember);
}