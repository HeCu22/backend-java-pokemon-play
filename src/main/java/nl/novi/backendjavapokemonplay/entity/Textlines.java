package nl.novi.backendjavapokemonplay.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "textlines")
public class Textlines {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String textline;

    @ManyToOne
    @JoinColumn(name = "pokemonplay_id")
    public PokemonPlay pokemonplay;

    public Textlines(Long id, String textline, PokemonPlay pokemonplay) {
    this.id = id;
    this.textline = textline;
    this.pokemonplay = pokemonplay;

    }

    public Textlines (){};

    public Long getId() {
        return id;
    }

    public String getTextline() {
        return textline;
    }

    public PokemonPlay getPokemonplay() {
        return pokemonplay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTextline(String textline) {
        this.textline = textline;
    }

    public void setPokemonplay(PokemonPlay pokemonplay) {
        this.pokemonplay = pokemonplay;
    }
}


