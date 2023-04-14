package trash_project.demo.member.dto;

import lombok.*;
import trash_project.demo.member.entity.MemberEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long no;
    private String memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setNo(memberEntity.getNo());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setMemberId(memberEntity.getMemberId());

        return memberDTO;
    }
}
