package ssafy.project.jobterview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"), //관리자
    ROLE_MEMBER("ROLE_MEMBER"), //이메일 인증 완료된 사용자
    ROLE_UNVERIFIED("ROLE_UNVERIFIED"), //이메일 인증이 안된 사용자
    ROLE_REPORTED("ROLE_REPORTED"),
    ROLE_WITHDRAWN("ROLE_WITHDRAWN");

    private String description;
}
