package nl.novi.backendjavapokemonplay.controller;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;

import nl.novi.backendjavapokemonplay.service.PokemonPlayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pokemonplay")
public class PokemonPlayController {

    private PokemonPlayService pokemonPlayService;

    public PokemonPlayController(PokemonPlayService pokemonPlayService) {
        this.pokemonPlayService = pokemonPlayService;
    }



    //Build create PokemonPlay REST API

    @PostMapping
    public ResponseEntity<PokemonPlayDto> createPokemonPlay(@RequestBody PokemonPlayDto pokemonPlayDto) {
        PokemonPlayDto savedPokemonPlay = pokemonPlayService.createPokemonPlay(pokemonPlayDto);
        return new ResponseEntity<>(savedPokemonPlay, HttpStatus.CREATED);
    }

    //Build get PokemonPlay by id REST API

    @GetMapping("{id}")
    public ResponseEntity<PokemonPlayDto> getPokemonPlayById(@PathVariable("id") Long pokemonPlayId) {
        PokemonPlayDto pokemonPlayDto = pokemonPlayService.getPokemonPlayById(pokemonPlayId);
        return ResponseEntity.ok(pokemonPlayDto);
    }

    @PutMapping("{name}")
    public ResponseEntity<PokemonPlayDto> updatePokemonPlay(@PathVariable("name") String pokemonPlayName, @RequestBody PokemonPlayDto updatedPokemonplay) {

        PokemonPlayDto pokemonPlayDto = pokemonPlayService.updatePokemonPlay(pokemonPlayName, updatedPokemonplay);
        return ResponseEntity.ok(pokemonPlayDto);
    }

//    @GetMapping("/enterplay/{id}")
//     public ResponseEntity<PokemonPlayDto> enterPokemonPlayById(@PathVariable("id") Long pokemonPlayId) {
//        PokemonPlayDto pokemonPlayDto = pokemonPlayService.enterPokemonPlay(pokemonPlayId);
//        PokemonGymOwner gymOwner1 = new PokemonGymOwner("Brock", "Pewter City", null);
//       pokemonGym.chooseGymPokemon(gymOwner1);
//        return ResponseEntity.ok(pokemonPlayDto);
//    }

//    //Build update PokemonPlay REST API
//    @PutMapping("{id}")
//    public ResponseEntity<PokemonPlayDto> updatePokemonPlay(@PathVariable("id") Long pokemonPlayId,
//                                                            @RequestBody PokemonPlayDto updatePokemonPlay) {
//        PokemonPlayDto pokemonPlayDto = pokemonPlayService.updatePokemonPlay(pokemonPlayId, updatePokemonPlay);
//        return ResponseEntity.ok(pokemonPlayDto);
//
//    }

}
