package ssafy.project.jobterview.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class ReportId implements Serializable {

    @Column(name = "reporter_id")
    private Long reporterId;

    @Column(name = "reported_id")
    private Long reportedId;
}
