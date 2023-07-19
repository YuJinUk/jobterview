package ssafy.project.jobterview.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;

import javax.persistence.EntityManager;

import java.util.List;

import static ssafy.project.jobterview.domain.QReport.report;

public class ReportRepositoryImpl implements ReportCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ReportRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<Report> searchByReportedMember(Member reportedMember, Pageable pageable) {
        BooleanExpression condition = report.reportedMember.eq(reportedMember);
        List<Report> reportList = queryFactory
                .selectFrom(report)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(report.count())
                .from(report)
                .where(condition)
                .fetchOne();
        return new PageImpl<>(reportList, pageable, count);
    }
}
