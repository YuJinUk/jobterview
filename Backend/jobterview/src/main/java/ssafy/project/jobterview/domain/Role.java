package ssafy.project.jobterview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"), //관리자
    ROLE_SOCIAL("ROLE_SOCIAL"), // 소셜로 로그인한 사람
    ROLE_LOCAL("ROLE_LOCAL"), // 로컬로 로그인한 사람
    ROLE_UNVERIFIED("ROLE_UNVERIFIED"), //이메일 인증이 안된 사용자
    ROLE_REPORTED_SOCIAL("ROLE_REPORTED_SOCIAL"),
    ROLE_REPORTED_LOCAL("ROLE_REPORTED_LOCAL"),
    ROLE_WITHDRAWN("ROLE_WITHDRAWN");
    private String description;
}
