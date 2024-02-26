package nl.novi.backendjavapokemonplay.service;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.entity.PokemonPlay;
import nl.novi.backendjavapokemonplay.entity.Textlines;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TextlinesService {


    PokemonPlayDto getPokemonPlayByName(String name);

    TextlinesDto addTextline(TextlinesDto textlinesDto);

    TextlinesDto getTextline(Long pokemonPlayid);

    List<TextlinesDto> getAllTextlinesByPokemon(PokemonPlay pokemonPlay);

    List<TextlinesDto> getAllTextlines();

    List<TextlinesDto> mapTextlinesToDtoList(List<Textlines> textlines);

}
