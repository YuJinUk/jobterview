package ssafy.project.jobterview.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Report extends BaseTimeEntity {

    @EmbeddedId
    @Column(name = "report_id")
    private ReportId reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    @MapsId("reporterId")
    private Member reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_member_id")
    @MapsId("reportedMemberId")
    private Member reportedMember;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(nullable = false)
    private String reason;
}
