package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;
import ssafy.project.jobterview.domain.ReportId;

import java.util.List;

@Transactional(readOnly = true)
public interface ReportRepository extends JpaRepository<Report, ReportId>, ReportCustomRepository {
    List<Report> findAllByReportedMember(Member m);
}
