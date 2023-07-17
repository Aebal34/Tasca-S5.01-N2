package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Repositories;

import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {
}
