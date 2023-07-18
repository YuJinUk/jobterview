package ssafy.project.jobterview.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Report extends BaseTimeEntity {

    @EmbeddedId
    private ReportId reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    @MapsId("reporterId")
    private Member reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_id")
    @MapsId("reportedId")
    private Member reportedMember;

    @Column(nullable = false)
    private String reason;
}
