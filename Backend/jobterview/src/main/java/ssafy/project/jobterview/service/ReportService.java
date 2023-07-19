package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;
import ssafy.project.jobterview.domain.ReportId;

public interface ReportService {
    Report save(Report r);
    Page<Report> findAll(Pageable pageable);
    public Page<Report> findByReportedMember(Member reportedMember, Pageable pageable);
    void deleteByReportId(ReportId reportId);
}
