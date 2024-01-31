package nl.novi.backendjavapokemonplay.entity;

import java.util.List;

public class PokemonGymOwner extends PokemonTrainer {
    private String town;

    public PokemonGymOwner(String town, String name, List<Pokemon> pokemons) {
        super(name, pokemons);

        this.town = town;
    }

    public String getTown() {
        return town;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return super.getPokemons();
    }

    public void setTown(String town) {
        this.town = town;
    }


}
