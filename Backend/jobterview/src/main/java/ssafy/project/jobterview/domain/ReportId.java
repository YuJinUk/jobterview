package ssafy.project.jobterview.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportId implements Serializable {

    @Column(name = "reporter_id")
    private Long reporterId;

    @Column(name = "reported_member_id")
    private Long reportedMemberId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportId reportId = (ReportId) o;
        return Objects.equals(getReporterId(), reportId.getReporterId()) && Objects.equals(getReportedMemberId(), reportId.getReportedMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReporterId(), getReportedMemberId());
    }
}
