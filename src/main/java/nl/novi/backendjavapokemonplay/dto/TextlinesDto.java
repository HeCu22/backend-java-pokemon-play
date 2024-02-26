package nl.novi.backendjavapokemonplay.dto;

import nl.novi.backendjavapokemonplay.entity.PokemonPlay;

public class TextlinesDto {


    public Long id;

    public String textline;

    private PokemonPlayDto pokemonplayDto;


    public TextlinesDto(Long id, String textline) {
        this.id = id;
        this.textline = textline;

    }

    public TextlinesDto() {

    }



    public Long getId() {
        return id;
    }

    public String getTextline() {
        return textline;
    }

    public PokemonPlayDto getPokemonplayDto() {
        return pokemonplayDto;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public void setTextline(String textline) {
        this.textline = textline;
    }

    public void setPokemonplayDto(PokemonPlayDto pokemonplayDto) {
        this.pokemonplayDto = pokemonplayDto;
    }
}



