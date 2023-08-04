package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;

import java.util.List;

public interface QuestionCustomRepository {
    List<Question> searchByRandom(int count);

    Page<Question> searchByCategory(Category c, Pageable pageable);
}

