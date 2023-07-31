package ssafy.project.jobterview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_admin("ROLE_admin"),
    ROLE_user("ROLE_user");

    private String description;

}
