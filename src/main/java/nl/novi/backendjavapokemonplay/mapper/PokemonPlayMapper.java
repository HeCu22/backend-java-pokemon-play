package nl.novi.backendjavapokemonplay.mapper;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.entity.PokemonPlay;

public class PokemonPlayMapper {
    public static PokemonPlayDto mapToPokemonPlayDto(PokemonPlay pokemonPlay) {
        return new PokemonPlayDto(
                pokemonPlay.getId(),
                pokemonPlay.getNamePlayerA(),
                pokemonPlay.getCardPlayerA(),
                pokemonPlay.getNameGymOwner(),
                pokemonPlay.getCardGymOwner(),
                pokemonPlay.getUsedPokemon()
        );
    }


    public static PokemonPlay mapToPokemonPlay (PokemonPlayDto pokemonPlayDto) {
        return new PokemonPlay(
                pokemonPlayDto.getId(),
                pokemonPlayDto.getNamePlayerA(),
                pokemonPlayDto.getCardPlayerA(),
                pokemonPlayDto.getNameGymOwner(),
                pokemonPlayDto.getCardGymOwner(),
                pokemonPlayDto.getUsedPokemon()

        );
    }
}
