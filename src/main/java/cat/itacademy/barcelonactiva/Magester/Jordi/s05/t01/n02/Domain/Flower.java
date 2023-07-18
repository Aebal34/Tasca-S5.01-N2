package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flowers")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Flower id", example = "1")
    Integer pk_ID;

    @Schema(name = "Flower name", example = "Sunflower")
    String name;

    @Schema(name = "Flower's country of procedence", example = "Italy")
    String country;
}
