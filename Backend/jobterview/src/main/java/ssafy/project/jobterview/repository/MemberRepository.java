package ssafy.project.jobterview.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Role;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Page<Member> findByNicknameContains(Pageable pageable, String keyword);

    Page<Member> findByNicknameContainsAndRole(Pageable pageable, String keyword, Role role);

    Page<Member> findByEmailContains(Pageable pageable, String keyword);

    Page<Member> findAll(Pageable pageable);

    Page<Member> findByRole(Role role, Pageable pageable);

    Long countByRole(Role role);
}

