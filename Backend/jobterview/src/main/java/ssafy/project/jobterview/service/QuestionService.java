package ssafy.project.jobterview.service;

import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Question save(Question q);
    Question update(Question q);
    List<Question> findAll();
    Optional<Question> findAllByCategory(Category c);
    Question findById(Long questionId);
    void deleteById(Long questionId);
    List<Question> findByRandom();
}
