package trash_project.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trash_project.demo.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    // 아이디로 회원 정보 조회 (select * from member_table where member_Id=?)
    Optional<MemberEntity> findByMemberId(String memberId);

}
