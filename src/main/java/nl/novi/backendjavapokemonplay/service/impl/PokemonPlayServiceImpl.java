package nl.novi.backendjavapokemonplay.service.impl;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.entity.*;
import nl.novi.backendjavapokemonplay.exception.ResourceNotFoundException;
import nl.novi.backendjavapokemonplay.mapper.PokemonPlayMapper;
import nl.novi.backendjavapokemonplay.repository.PokemonPlayRepo;
import nl.novi.backendjavapokemonplay.service.PokemonGym;
import nl.novi.backendjavapokemonplay.service.PokemonPlayService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonPlayServiceImpl implements PokemonPlayService {

    private PokemonPlayRepo pokemonPlayRepo;

//    private static final FirePokemon charizard = new FirePokemon("Char
//    izard", 76, 150, "firenougats", "GRRRRRRRRRRRRRRR");
//    private static List<Pokemon> pokemons = Arrays.asList(charizard);
//    private static final WaterPokemon blastoise = new WaterPokemon("Blastoise", 40, 110, "Pokeflakes", "Blaaaaasssssstooooiiiiissss");
//    private static final GrassPokemon venusaur = new GrassPokemon("Venusaur", 50, 135, "Pokeleafs", "Veeeeeeeeennnnnuuuuuusaur");
//    private static final GrassPokemon ditto = new GrassPokemon("Ditto", 60, 140, "Everything", "Dittto diiiito ");
//    private static final ElectricPokemon raichu = new ElectricPokemon("Raichu", 80, 160, "Pokebrocks", "Raaaaiiiiicccchhhhuuuuuuu!!!!");
//    private static final WaterPokemon gyarados = new WaterPokemon("Gyarados", 90, 180, "Pokeflakes", "Gyaaaaaaaaarrrraaaadoooos");

    public PokemonPlayServiceImpl(PokemonPlayRepo pokemonPlayRepo){
        this.pokemonPlayRepo = pokemonPlayRepo;
    }

    public PokemonPlayRepo getPokemonPlayRepo() {
        return pokemonPlayRepo;
    }


    public void setPokemonPlayRepo(PokemonPlayRepo pokemonPlayRepo) {
        this.pokemonPlayRepo = pokemonPlayRepo;
    }


    @Override
    public Pokemon selectPokemon(String cardPlayerA, PokemonTrainer playerA) {
        return null;
    }

    @Override
    public PokemonPlayDto createPokemonPlay(PokemonPlayDto pokemonPlayDto) {
        PokemonPlay pokemonPlay = PokemonPlayMapper.mapToPokemonPlay(pokemonPlayDto);
        PokemonPlay savePokemonPlay = pokemonPlayRepo.save(pokemonPlay);

        return PokemonPlayMapper.mapToPokemonPlayDto(savePokemonPlay);
    }

    @Override
    public PokemonPlayDto getPokemonPlayById(Long pokemonPlayId) {
        PokemonPlay pokemonPlay = pokemonPlayRepo.findById(pokemonPlayId)
                .orElseThrow(() -> new ResourceNotFoundException("PokemonPlay does not exist with geven id :" + pokemonPlayId));
        return PokemonPlayMapper.mapToPokemonPlayDto(pokemonPlay);
    }


//    @Override
//    public PokemonPlayDto enterPokemonPlay(Long pokemonPlayId, List<Pokemon> pokemons) {
//        return null;
//    }
//
//
//    @Override
//    public PokemonPlayDto enterPokemonPlay(Long pokemonPlayId) {
//
//        PokemonPlay pokemonPlay = pokemonPlayRepo.findById(pokemonPlayId)
//                .orElseThrow(() -> new ResourceNotFoundException("PokemonPlay does not exist with geven id :" + pokemonPlayId));
//        PokemonGymOwner gymOwner1 = new PokemonGymOwner("Brock", "Pewter City", pokemons);
//
//
//        return PokemonPlayMapper.mapToPokemonPlayDto(pokemonPlay);
//
//    }

//    @Override
//    public PokemonPlayDto updatePokemonPlay(String pokemonPlayName, PokemonPlayDto updatedPokemonPlay) {
//        return null;
//    }

    @Override
    public PokemonPlayDto updatePokemonPlay(String pokemonPlayName, PokemonPlayDto updatedPokemonPlay) {
        PokemonPlay pokemonPlay1 = pokemonPlayRepo.findPokemonPlayByNamePlayerAEqualsIgnoreCase(pokemonPlayName);
        PokemonPlay pokemonPlay = pokemonPlayRepo.findById((pokemonPlay1.getId())).orElseThrow(
                () -> new ResourceNotFoundException("Pokemonplay does not exist with given id: " + pokemonPlayName)
        );
        pokemonPlay.setNamePlayerA(updatedPokemonPlay.getNamePlayerA());
        pokemonPlay.setCardPlayerA(updatedPokemonPlay.getCardPlayerA());
        pokemonPlay.setNameGymOwner(updatedPokemonPlay.getNameGymOwner());
        pokemonPlay.setCardGymOwner(updatedPokemonPlay.getCardGymOwner());

        PokemonPlay updatedPokemonObj = pokemonPlayRepo.save(pokemonPlay);
        return PokemonPlayMapper.mapToPokemonPlayDto(updatedPokemonObj);
    }
}
