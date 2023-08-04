package ssafy.project.jobterview.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.QMate;

import java.util.List;

@RequiredArgsConstructor
public class MateRepositoryImpl implements MateCustomRepository{

    private final JPAQueryFactory queryFactory;

    public Page<Mate> findAllByFromMember(Pageable pageable, String nickname){
        QMate qMate= QMate.mate;
        List<Mate> mateList = queryFactory.selectFrom(qMate)
                .where(qMate.fromMember.nickname.eq(nickname)).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(qMate.count())
                .from(qMate)
                .where(qMate.fromMember.nickname.eq(nickname))
                .fetchOne();

        return new PageImpl<>(mateList,pageable, count);
    }



}

