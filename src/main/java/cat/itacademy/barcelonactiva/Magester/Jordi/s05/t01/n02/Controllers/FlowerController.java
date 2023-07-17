package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Controllers;

import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Dto.FlowerDto;
import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Services.IFlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private IFlowerService flowerService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){

        List<FlowerDto> flowers = flowerService.getAll().getBody();
        if(flowers != null) {
            return ResponseEntity.ok(flowers);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Not found.");
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id){
        FlowerDto flowerDto = flowerService.getOne(id).getBody();
        if(flowerDto != null){
            return ResponseEntity.ok(flowerDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Not found.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody FlowerDto flowerDto){

        return flowerService.add(flowerDto);
    }

    @PutMapping("update")
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestParam(required = false)String name, @RequestParam(required = false)String country){

        return flowerService.update(id, name, country);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@RequestParam Integer id){

        return flowerService.delete(id);
    }
}
