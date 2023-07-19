package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;
import ssafy.project.jobterview.domain.ReportId;
import ssafy.project.jobterview.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    /**
     * 접수된 신고 저장
     * @param r 접수된 신고
     * @return
     */
    @Override
    public Report save(Report r) {
        return reportRepository.save(r);
    }

    /**
     * 모든 신고 목록 조회
     * @param pageable 페이징 정보
     * @return
     */
    @Override
    public Page<Report> findAll(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }


    /**
     * 피신고자 기준으로 신고 목록 조회
     * @param reportedMember 피신고자
     * @param pageable 페이징 정보
     * @return 
     */
    @Override
    public Page<Report> findByReportedMember(Member reportedMember, Pageable pageable) {
//        Member m = memberRepository.findById(reported_id);
        return reportRepository.searchByReportedMember(reportedMember, pageable);
    }

    /**
     * Member 작성되면 controller에서 request로 들어온 nickname을 받아 member 객체 찾는 것으로 수정
     */
//    @Override
//    public Page<Report> findByReportedMember(String nickname, Pageable pageable) {
////        Member m = memberRepository.findByNickname(nickname);
////        return reportRepository.findAllByReportedMember(m);
//    }
//
    /**
     * 신고 삭제
     * @param reportId 삭제할 신고 id
     */
    @Override
    public void deleteByReportId(ReportId reportId) {
        reportRepository.deleteById(reportId);
    }
}
