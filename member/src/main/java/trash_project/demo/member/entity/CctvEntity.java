package trash_project.demo.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import trash_project.demo.member.dto.CctvDTO;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "cctv")
public class CctvEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column
    private String cctvAddressStreet;

    @Column
    private String cctvAddressDetail;

    @Column
    private String cctvAlias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_no")
    private MemberEntity memberEntity;


    public static CctvEntity toCctvEntity(CctvDTO cctvDTO) {
        CctvEntity cctvEntity = new CctvEntity();
        cctvEntity.setCctvAddressStreet(cctvDTO.getCctvAddressStreet());
        cctvEntity.setCctvAddressDetail(cctvDTO.getCctvAddressDetail());
        cctvEntity.setCctvAlias(cctvDTO.getCctvAlias());

        return cctvEntity;
    }

}
