package nl.novi.backendjavapokemonplay.dto;

import java.util.List;

public class PokemonPlayDto {
    private Long id;
    private String namePlayerA;
    private String cardPlayerA;
    private String nameGymOwner;
    private String cardGymOwner;
    private String usedPokemon;

    public PokemonPlayDto(Long id, String namePlayerA, String cardPlayerA, String nameGymOwner, String cardGymOwner, String usedPokemon) {
        this.id = id;
        this.namePlayerA = namePlayerA;
        this.cardPlayerA = cardPlayerA;
        this.nameGymOwner = nameGymOwner;
        this.cardGymOwner = cardGymOwner;
        this.usedPokemon = usedPokemon;
    }

    public PokemonPlayDto() {
    }

    public Long getId() {
        return id;
    }

    public String getNamePlayerA() {
        return namePlayerA;
    }

    public String getCardPlayerA() {
        return cardPlayerA;
    }

    public String getNameGymOwner() {
        return nameGymOwner;
    }

    public String getCardGymOwner() {
        return cardGymOwner;
    }

    public String getUsedPokemon() {
        return usedPokemon;
    }

    public void setId(Long id) {
        id = id;
    }

    public void setNamePlayerA(String namePlayerA) {
        this.namePlayerA = namePlayerA;
    }

    public void setCardPlayerA(String cardPlayerA) {
        this.cardPlayerA = cardPlayerA;
    }

    public void setCardGymOwner(String cardGymOwner) {
        this.cardGymOwner = cardGymOwner;
    }

    public void setUsedPokemon(String usedPokemon) {
        this.usedPokemon = usedPokemon;
    }

    public void setNameGymOwner(String nameGymOwner) {
        this.nameGymOwner = nameGymOwner;
    }
}
