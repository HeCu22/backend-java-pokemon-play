package nl.novi.backendjavapokemonplay.service;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.entity.Pokemon;
import nl.novi.backendjavapokemonplay.entity.PokemonTrainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonPlayService {
    Pokemon selectPokemon(String cardPlayerA, PokemonTrainer playerA);

    PokemonPlayDto createPokemonPlay(PokemonPlayDto pokemonPlayDto);

    PokemonPlayDto getPokemonPlayById(Long pokemonPlayId);


    PokemonPlayDto updatePokemonPlay(String pokemonPlayName, PokemonPlayDto updatedPokemonPlay);


}
