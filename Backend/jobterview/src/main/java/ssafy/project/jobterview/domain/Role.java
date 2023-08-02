package ssafy.project.jobterview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    admin("admin"),
    user("user");

    private String description;
}
