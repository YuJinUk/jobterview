package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
