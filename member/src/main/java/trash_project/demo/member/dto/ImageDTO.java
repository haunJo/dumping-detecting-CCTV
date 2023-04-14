package trash_project.demo.member.dto;

import lombok.*;
import trash_project.demo.member.entity.ImageEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageDTO {
    private Long no;
    private String imageFilename;

    public static ImageDTO toImageDTO(ImageEntity imageEntity) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setNo(imageEntity.getNo());
        imageDTO.setImageFilename(imageEntity.getImageFilename());

        return imageDTO;
    }
}
