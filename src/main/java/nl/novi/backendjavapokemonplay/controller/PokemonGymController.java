package nl.novi.backendjavapokemonplay.controller;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.entity.*;
import nl.novi.backendjavapokemonplay.service.PokemonGym;
import nl.novi.backendjavapokemonplay.service.PokemonPlayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pokemonplay")
public class PokemonGymController {

    private PokemonGym pokemonGym;

    public PokemonGymController(PokemonGym pokemonGym) {
        this.pokemonGym = pokemonGym;
    }

    @GetMapping("/enterplay/{name}")
    public ResponseEntity<PokemonPlayDto> enterPokemonPlayByName(@PathVariable("name") String namePlayerA) {
        PokemonTrainer playerA = new PokemonTrainer(namePlayerA, null);
        PokemonPlayDto pokemonplayenteredthegym = pokemonGym.enterTheGym(playerA);
        return ResponseEntity.ok(pokemonplayenteredthegym);
    }

    @GetMapping("/entercard/{name}")
    public ResponseEntity<List<Pokemon>> enterCardByName(@PathVariable("name") String namePlayerA) {
            PokemonTrainer playerA = new PokemonTrainer(namePlayerA, null);
        List<Pokemon> pokemonplayenteredthegym = pokemonGym.enterCard(playerA);
        return ResponseEntity.ok(pokemonplayenteredthegym);
    }

    @PutMapping("/enterfight/{id}")
    public ResponseEntity<Textlines> enterFight(@PathVariable("id")  Long pokemonPlayId, @RequestBody PokemonPlayDto pokemonPlayDto) {

       Textlines textlinesToReturn = pokemonGym.enterFight(pokemonPlayDto);
            return ResponseEntity.ok(textlinesToReturn);
    }

    @PutMapping("/nextround/{answer}")
    public ResponseEntity<Textlines> fightRoundNext(@PathVariable("answer")  String answer, @RequestBody PokemonPlayDto pokemonPlayDto) {
        Textlines textlinesToReturn;
        if (answer.equals("a")) {
         textlinesToReturn = pokemonGym.fightRoundNext(pokemonPlayDto);}
        else {
            textlinesToReturn = pokemonGym.performAttack(answer, pokemonPlayDto);
        }

        return ResponseEntity.ok(textlinesToReturn);
    }

}
