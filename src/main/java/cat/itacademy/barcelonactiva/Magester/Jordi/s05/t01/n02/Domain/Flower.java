package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Domain;

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
    Integer pk_ID;

    String name;

    String country;
}
