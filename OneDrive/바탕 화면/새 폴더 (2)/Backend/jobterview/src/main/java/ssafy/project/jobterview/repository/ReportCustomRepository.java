package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;

public interface ReportCustomRepository {
    Page<Report> searchByReportedMember(Member reportedMember, Pageable pageable);
}
