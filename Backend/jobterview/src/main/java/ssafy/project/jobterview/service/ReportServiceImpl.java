package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;
import ssafy.project.jobterview.domain.ReportId;
import ssafy.project.jobterview.dto.ReportDto;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    /**
     * 접수된 신고 저장 
     * Dto의 닉네임으로 Member를 조회하여 Report Entity 생성하여 저장
     * @param r
     * @return
     */
    @Override
    public Report save(ReportDto r) {
        Member reporter = getMember(r.getReporterNickname());
        Member reported = getMember(r.getReportedNickname());

        ReportId reportId = new ReportId(reporter.getMemberId(), reported.getMemberId());
        Report report = new Report(reportId, reporter, reported, r.getReason());
        return reportRepository.save(report);
    }

    /**
     * 모든 신고 목록 조회
     * @param pageable 페이징 정보
     * @return
     */
    @Override
    public Page<Report> findAll(Pageable pageable) {
        Page<Report> reportList = reportRepository.findAll(pageable);
        if(reportList.isEmpty()) {
            throw new IllegalArgumentException("신고 목록이 없음");
        } else {
            return reportList;
        }
    }

    /**
     * 해당 유저에 대해 접수된 신고 목록 조회
     * @param reportedNickname
     * @param pageable
     * @return
     */
    @Override
    public Page<Report> findAllByReportedMember(String reportedNickname, Pageable pageable) {
        Member m = getMember(reportedNickname);
        return reportRepository.searchByReportedMember(m, pageable);
    }

    /**
     * 신고 삭제
     * @param r 삭제할 신고 Dto
     */
    @Override
    public void delete(ReportDto r) {
        Member reporter = getMember(r.getReporterNickname());
        Member reported = getMember(r.getReportedNickname());
        ReportId reportId = new ReportId(reporter.getMemberId(), reported.getMemberId());
        reportRepository.deleteById(reportId);
    }

    private Member getMember(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
    }
}
