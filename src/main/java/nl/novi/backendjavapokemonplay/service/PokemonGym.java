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

    Textlines fightRound(PokemonTrainer trainer, PokemonGymOwner owner, Pokemon pokemon, Pokemon gymPokemon);


    Pokemon chooseGymPokemon(PokemonGymOwner gymOwner);

    List<Pokemon> choosePokemon(PokemonTrainer trainer);

    int randomAttackByGymOwner();

    Textlines chooseAttackPlayer(Pokemon p);

    Textlines performAttack(String attack, PokemonPlayDto pokemonPlayDto);

    Textlines performAttackPlayer(Pokemon pokemon, Pokemon gymPokemon, String attack);

    Textlines gymOwnerAttacks(Pokemon gymPokemon, Pokemon pokemon);

    Textlines attackOrChange(String choice, Pokemon pokemon, Pokemon gymPokemon, PokemonTrainer trainer, PokemonGymOwner gym);


    Textlines enterFight(PokemonPlayDto pokemonPlayDto);


    Textlines fightRoundNext(PokemonPlayDto pokemonPlayDto);
}
