package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Services;

import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Dto.FlowerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFlowerService {

    ResponseEntity<String> add(FlowerDto branchOfficeDto);

    ResponseEntity<String> update(Integer id, String name, String country);

    ResponseEntity<String> delete(Integer pk_Id);

    ResponseEntity<FlowerDto> getOne(Integer pk_ID);

    ResponseEntity<List<FlowerDto>> getAll();
}
