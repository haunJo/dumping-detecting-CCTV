package trash_project.demo.member.dto;

import lombok.*;
import trash_project.demo.member.entity.CctvEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CctvDTO {
    private Long no;
    private String cctvAddressStreet;
    private String cctvAddressDetail;
    private String cctvAlias;

    public static CctvDTO toCctvDTO(CctvEntity cctvEntity) {
        CctvDTO cctvDTO = new CctvDTO();
        cctvDTO.setNo(cctvEntity.getNo());
        cctvDTO.setCctvAddressStreet(cctvEntity.getCctvAddressStreet());
        cctvDTO.setCctvAddressDetail(cctvEntity.getCctvAddressDetail());
        cctvDTO.setCctvAlias(cctvEntity.getCctvAlias());

        return cctvDTO;
    }
}
