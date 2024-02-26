package nl.novi.backendjavapokemonplay.repository;

import nl.novi.backendjavapokemonplay.entity.Textlines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextlinesRepo extends JpaRepository <Textlines, Long> {
    List<Textlines> findAll();

}
