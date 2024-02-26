package nl.novi.backendjavapokemonplay.service;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonGym {

    PokemonPlayDto enterTheGym(PokemonTrainer player1);
    List<Pokemon> enterCard(PokemonTrainer player1);

    void printPokemon(List<Pokemon> pokemons);

    Pokemon selectPokemon(String pokemon, PokemonTrainer trainer);

    void fightRound(PokemonPlayDto pokemonPlayDto);


    Pokemon chooseGymPokemon(PokemonGymOwner gymOwner);

    List<Pokemon> choosePokemon(PokemonTrainer trainer);

    int randomAttackByGymOwner();

    List<String> chooseAttackPlayer(Pokemon p);

    void performAttack(String attack, PokemonPlayDto pokemonPlayDto);

    List<String> performAttackPlayer(Pokemon pokemon, Pokemon gymPokemon, String attack);

    List<String> gymOwnerAttacks(Pokemon gymPokemon, Pokemon pokemon);

    void attackOrChange(String choice, Pokemon pokemon, Pokemon gymPokemon, PokemonTrainer trainer, PokemonGymOwner gym);


    void enterFight(PokemonPlayDto pokemonPlayDto);


    void fightRoundNext(PokemonPlayDto pokemonPlayDto);

    // PokemonPlayDto getPokemonPlayByName(String pokemonPlayName);
}
