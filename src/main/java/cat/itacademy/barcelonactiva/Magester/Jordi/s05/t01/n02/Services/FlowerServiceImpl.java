package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Services;

import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Domain.Flower;
import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Dto.FlowerDto;
import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Repositories.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowerServiceImpl implements IFlowerService{

    @Autowired
    private FlowerRepository flowerRepository;

    @Override
    public ResponseEntity<String> add(FlowerDto flowerDto) {

        if (flowerDto != null) {
            Flower flower = new Flower();

            flower.setPk_ID(flowerDto.getPk_ID());
            flower.setName(flowerDto.getName());
            flower.setCountry(flowerDto.getCountry());

            flowerRepository.save(flower);
            return ResponseEntity.ok().body("200. Flower saved.");
        }else{
            return ResponseEntity.badRequest().body("Code 400. Some introduced data is wrong.");
        }
    }

    @Override
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestParam(required = false)String name, @RequestParam(required = false)String country) {

        Flower flower = flowerRepository.findById(id).orElse(null);
        if (flower != null) {
            if(name != null){
                flower.setName(name);
            }
            if(country != null){
                flower.setCountry(country);
            }
            flowerRepository.save(flower);
            return ResponseEntity.ok("200. Entity successfully updated.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Not found.");
        }
    }

    @Override
    public ResponseEntity<String> delete(Integer pk_ID) {

       // Flower flower = flowerRepository.findById(pk_ID).orElse(null);
        //if(flower != null){
            flowerRepository.deleteById(pk_ID);
            return ResponseEntity.ok("200. Entity removed successfully.");
       // }else{
       //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Entity not found");
       // }
    }

    @Override
    public ResponseEntity<FlowerDto> getOne(Integer pk_Id) {

        Flower flower = flowerRepository.findById(pk_Id).orElse(null);
        FlowerDto flowerDto = new FlowerDto();
        if (flower != null) {
            flowerDto.setPk_ID(flower.getPk_ID());
            flowerDto.setName(flower.getName());
            flowerDto.setCountry(flower.getCountry());
            return ResponseEntity.ok(flowerDto);
        }else{
            return ResponseEntity.ofNullable(flowerDto);
        }
    }

    @Override
    public ResponseEntity<List<FlowerDto>> getAll() {

        List<Flower> flowers = flowerRepository.findAll();
        List<FlowerDto> flowersDto = new ArrayList<>();
        for(Flower flower : flowers){
            flowersDto.add(new FlowerDto(flower.getPk_ID(),
                                            flower.getName(),
                                            flower.getCountry()));
        }

        return ResponseEntity.ok(flowersDto);
    }
}
