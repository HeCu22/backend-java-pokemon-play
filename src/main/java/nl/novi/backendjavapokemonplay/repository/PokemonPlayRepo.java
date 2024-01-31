package nl.novi.backendjavapokemonplay.repository;

import nl.novi.backendjavapokemonplay.entity.PokemonPlay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonPlayRepo extends JpaRepository <PokemonPlay, Long> {
    PokemonPlay findPokemonPlayByNamePlayerAEqualsIgnoreCase(String name);
}
