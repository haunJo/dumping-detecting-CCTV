package trash_project.demo.member.entity;


import lombok.Getter;
import lombok.Setter;
import trash_project.demo.member.dto.ImageDTO;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column
    private String imageFilename;

    @ManyToOne
    @JoinColumn(name="cctv_no")
    private CctvEntity cctvEntity;

    public static ImageEntity toImageEntity(ImageDTO imageDTO) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageFilename(imageDTO.getImageFilename());

        return imageEntity;
    }
}
