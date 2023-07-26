package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.domain.Report;

public interface ReportCustomRepository {
    public Page<Report> searchByReportedMember(Member reportedMember, Pageable pageable);
}
