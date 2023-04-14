package trash_project.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trash_project.demo.member.entity.ImageEntity;
import trash_project.demo.member.entity.MemberEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
}
