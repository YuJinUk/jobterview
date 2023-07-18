package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public Question update(Question q) {
        Question updateQuestion= questionRepository.findById(q.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다."));
        updateQuestion.setCategory(q.getCategory());
        updateQuestion.setContent(q.getContent());
        return updateQuestion;
    }

    @Override
    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
    @Override
    public Page<Question> findAllByCategory(Category c, Pageable pageable) {
        return questionRepository.searchByCategory(c, pageable);
    }

    @Override
    public Question findById(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다."));
    }

    @Override
    public void deleteById(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> findByRandom(int count) {
        long length = questionRepository.count();
        if(count > length) {
            throw new IllegalArgumentException("면접 질문의 개수가 부족합니다.");
        } else {
            return questionRepository.searchByRandom(count);
        }
    }
}
