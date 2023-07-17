package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MailCert extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "mail_cert_id")
    private Long mailCertId;

    @OneToOne(mappedBy = "mailCert")
    private Member member;

    @Column(name = "mail_key", nullable = false)
    private int mailKey;

    @Column(name = "mail_Auth", nullable = false)
    @ColumnDefault("0")
    private boolean mailAuth;
}
