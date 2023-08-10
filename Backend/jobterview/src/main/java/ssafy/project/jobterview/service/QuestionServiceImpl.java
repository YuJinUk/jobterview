package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 질문 신규 등록
     *
     * @param q : 신규 등록 질문 엔티티
     * @return
     */
    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }

    /**
     * 질문 정보 수정
     *
     * @param q : 수정 질문 엔티티
     * @return
     */
    @Override
    public Question update(Question q) {
        Question updateQuestion = questionRepository.findById(q.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다."));
        updateQuestion.setCategory(q.getCategory());
        updateQuestion.setContent(q.getContent());
        questionRepository.save(updateQuestion);
        return updateQuestion;
    }

    /**
     * 질문 목록 전체 조회
     *
     * @return 조회된 질문 목록
     */
    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    /**
     * 질문 목록 전체 조회
     *
     * @param pageable : 페이징 정보
     * @return 조회된 질문 목록 (페이지당 10개)
     */
    @Override
    public Page<Question> findAllWithPaging(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    /**
     * 선택한 종류의 질문 전체 조회
     *
     * @param c        : 종류 (비전, 가치관, 직무, ...)
     * @param pageable : 페이징 정보
     * @return 조회된 질문 목록 (페이지당 10개)
     */
    @Override
    public Page<Question> findAllByCategory(Category c, Pageable pageable) {
        return questionRepository.searchByCategory(c, pageable);
    }

    /**
     * Id에 해당하는 질문 조회
     *
     * @param questionId : 삭제할 질문의 id
     * @return 조회된 질문 엔티티
     */
    @Override
    public Question findById(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다."));
    }

    /**
     * Id에 해당하는 질문 삭제
     *
     * @param questionId : 삭제할 질문의 id
     */
    @Override
    public void deleteById(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    /**
     * 랜덤으로 질문 선택
     *
     * @param count : 랜덤으로 선택할 질문 개수
     * @return 랜덤으로 선택된 질문 리스트
     */
    @Override
    public List<Question> findByRandom(int count) {
        long length = questionRepository.count();
        if (count > length) {
            throw new IllegalArgumentException("면접 질문의 개수가 부족합니다.");
        } else {
            return questionRepository.searchByRandom(count);
        }
    }
}
