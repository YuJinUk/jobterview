package ssafy.project.jobterview.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;

import javax.persistence.EntityManager;
import java.util.List;

import static ssafy.project.jobterview.domain.QQuestion.question;

public class QuestionRepositoryImpl implements QuestionCustomRepository {

    private final JPAQueryFactory queryFactory;

    public QuestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 질문 랜덤 선택
     * @param count 선택 질문 개수
     * @return 선택된 질문 리스트
     */
    @Override
    public List<Question> searchByRandom(int count) {
        return queryFactory
                .selectFrom(question)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }

    /**
     * 분류별 질문 조회
     * @param c 선택할 질문 분류
     * @param pageable 페이징 정보
     * @return 조회된 질문 페이지
     */
    @Override
    public Page<Question> searchByCategory(Category c, Pageable pageable) {
        BooleanExpression condition = question.category.eq(c);
        List<Question> questionList =  queryFactory.
                selectFrom(question)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(question.count())
                .from(question)
                .where(condition)
                .fetchOne();
        return new PageImpl<>(questionList, pageable, count);
    }
}
