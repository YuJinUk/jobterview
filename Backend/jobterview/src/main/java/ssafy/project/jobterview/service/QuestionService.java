package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Question save(Question q);
    Question update(Question q);
    List<Question> findAll();
    Page<Question> findAllWithPaging(Pageable pageable);
    Page<Question> findAllByCategory(Category c, Pageable pageable);
    Question findById(Long questionId);
    void deleteById(Long questionId);
    List<Question> findByRandom(int count);
}
