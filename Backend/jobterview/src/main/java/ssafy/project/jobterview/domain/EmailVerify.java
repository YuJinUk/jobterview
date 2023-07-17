package ssafy.project.jobterview.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class EmailVerify {

    @Column(name = "mail_key", nullable = false)
    private int mailKey;

    @Column(name = "mail_Auth", nullable = false)
    @ColumnDefault("0")
    private boolean mailAuth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailVerify that = (EmailVerify) o;
        return getMailKey() == that.getMailKey() && isMailAuth() == that.isMailAuth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMailKey(), isMailAuth());
    }
}
