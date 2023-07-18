package ssafy.project.jobterview.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.QQuestion;
import ssafy.project.jobterview.domain.Question;

import javax.persistence.EntityManager;
import java.util.List;

import static ssafy.project.jobterview.domain.QQuestion.question;

public class QuestionRepositoryImpl implements QuestionCustomRepository {

    private final JPAQueryFactory queryFactory;

    public QuestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<Question> searchByRandom(int count) {
        return queryFactory
                .selectFrom(question)
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }

    @Override
    public Page<Question> searchByCategory(Category c, Pageable pageable) {
        BooleanExpression condition = question.category.eq(c);
        return queryFactory.
                selectFrom(question)
                .where()
                .fetch();
    }
}
