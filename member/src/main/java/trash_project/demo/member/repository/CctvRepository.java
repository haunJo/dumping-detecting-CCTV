package trash_project.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trash_project.demo.member.entity.CctvEntity;

public interface CctvRepository extends JpaRepository<CctvEntity, String> {

}
