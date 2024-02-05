package nl.novi.backendjavapokemonplay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

//@Table(name = "pokemonplay")

@Entity
@Table(name = "pokemon_play")
public class PokemonPlay {

    @Id
    @GeneratedValue
         (strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name = "a name player A", nullable = false, unique = true)
    private String namePlayerA;

@Column(name = "b card Player A")
    private String cardPlayerA;

@Column(name = "c name gym owner")
    private String nameGymOwner;

@Column(name = "d card gym owner")
    private String cardGymOwner;

 @Column(name = "e used pokemon")
    private String usedPokemon;




    public PokemonPlay(Long id, String namePlayerA, String cardPlayerA, String nameGymOwner, String cardGymOwner, String usedPokemon) {
        this.id = id;
        this.namePlayerA = namePlayerA;
        this.cardPlayerA = cardPlayerA;
        this.nameGymOwner = nameGymOwner;
        this.cardGymOwner = cardGymOwner;
        this.usedPokemon = usedPokemon;
    }

    public PokemonPlay() {
    }

    // Dit is de target kant van de relatie. Er staat niks in de database
  ////  @OneToMany(mappedBy = "pokemon_play")
 //   @JsonIgnore
  //  List<PokemonCards> pokemon_cards;


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
        this.id = id;
    }

    public void setNamePlayerA(String namePlayerA) {
        this.namePlayerA = namePlayerA;
    }

    public void setCardPlayerA(String cardPlayerA) {
        this.cardPlayerA = cardPlayerA;
    }

    public void setNameGymOwner(String nameGymOwner) {
        this.nameGymOwner = nameGymOwner;
    }

    public void setCardGymOwner(String cardGymOwner) {
        this.cardGymOwner = cardGymOwner;
    }

    public void setUsedPokemon( String usedPokemon) {
        this.usedPokemon = usedPokemon;
    }
}
