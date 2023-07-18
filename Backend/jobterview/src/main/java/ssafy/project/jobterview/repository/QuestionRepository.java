package ssafy.project.jobterview.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;

import java.util.*;

@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionCustomRepository {
    Optional<Question> findAllByCategory(Category c);
}
