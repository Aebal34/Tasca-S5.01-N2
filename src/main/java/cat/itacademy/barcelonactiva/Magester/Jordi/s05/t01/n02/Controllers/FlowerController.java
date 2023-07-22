package cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Controllers;

import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Domain.Flower;
import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Dto.FlowerDto;
import cat.itacademy.barcelonactiva.Magester.Jordi.s05.t01.n02.Services.IFlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all flowers in database.")
    @ApiResponse(responseCode = "200", description = "Found flowers",
                    content = { @Content(mediaType = "application.json",
                        schema = @Schema(implementation = List.class))})
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){

        List<FlowerDto> flowers = flowerService.getAll().getBody();
        if(flowers != null) {
            return ResponseEntity.ok(flowers);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Not found.");
        }
    }

    @Operation(summary = "Get a flower by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the flower",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower not found",
                    content = @Content) })
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Object> getOne(@Parameter(description = "id of flower to be searched", name="id", example="1") @PathVariable("id") Integer id){
        FlowerDto flowerDto = flowerService.getOne(id).getBody();
        if(flowerDto != null){
            return ResponseEntity.ok(flowerDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Not found.");
        }
    }

    @Operation(summary = "Add a flower with a JSON body into database.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Flower successfully added.",
                    content={ @Content(mediaType = "application/json",
                            schema=@Schema(implementation = Flower.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, info provided is wrong",
                    content = @Content)
    })
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody FlowerDto flowerDto){

        return flowerService.add(flowerDto);
    }

    @Operation(summary = "Edit a flower in the database.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Flower successfully edited.",
                    content={ @Content(mediaType = "application/json",
                            schema=@Schema(implementation = Flower.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, info provided is wrong.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower not found.",
                    content = @Content)
    })
    @PutMapping("update")
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestParam(required = false)String name, @RequestParam(required = false)String country){

        return flowerService.update(id, name, country);
    }

    @Operation(summary = "Delete a flower in the database.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Flower successfully removed.",
                    content={ @Content(mediaType = "application/json",
                            schema=@Schema(implementation = Flower.class)) }),
            @ApiResponse(responseCode = "404", description = "Flower not found.",
                    content = @Content)
    })

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){

        return flowerService.delete(id);
    }
}
