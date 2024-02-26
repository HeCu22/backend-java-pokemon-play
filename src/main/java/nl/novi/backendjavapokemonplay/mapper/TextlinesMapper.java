package nl.novi.backendjavapokemonplay.mapper;

import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.entity.Textlines;


public class TextlinesMapper {
    public static TextlinesDto mapToTextlinesDto(Textlines textlines) {
        TextlinesDto dto = new TextlinesDto();
        dto.setId(textlines.getId());
        dto.setTextline(textlines.getTextline());
        dto.setPokemonplayDto(PokemonPlayMapper.mapToPokemonPlayDto(textlines.getPokemonplay()));
        return dto;

    }


    public static Textlines mapToTextlines(TextlinesDto textlinesDto) {
        var textlines = new Textlines();
        textlines.setTextline(textlinesDto.getTextline());
        textlines.setPokemonplay(PokemonPlayMapper.mapToPokemonPlay(textlinesDto.getPokemonplayDto()));
     return textlines;
    }
}
