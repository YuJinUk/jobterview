package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
