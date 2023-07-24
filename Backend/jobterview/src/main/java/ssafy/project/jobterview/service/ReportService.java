package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;
import ssafy.project.jobterview.domain.ReportId;
import ssafy.project.jobterview.dto.ReportDto;

public interface ReportService {
    Report save(ReportDto r);
    Page<Report> findAll(Pageable pageable);
    public Page<Report> findAllByReportedMember(String reportedNickname, Pageable pageable);
    void delete(ReportDto r);
}
