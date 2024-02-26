package nl.novi.backendjavapokemonplay.service.impl;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.entity.*;
import nl.novi.backendjavapokemonplay.exception.ResourceNotFoundException;
import nl.novi.backendjavapokemonplay.mapper.PokemonPlayMapper;
import nl.novi.backendjavapokemonplay.repository.PokemonPlayRepo;
import nl.novi.backendjavapokemonplay.service.PokemonGym;
import nl.novi.backendjavapokemonplay.service.PokemonPlayService;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonPlayServiceImpl implements PokemonPlayService {

    private PokemonPlayRepo pokemonPlayRepo;

    private TextlinesServiceImpl textlinesService;

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
        pokemonPlay.setUsedPokemon(updatedPokemonPlay.getUsedPokemon());

        PokemonPlay updatedPokemonObj = pokemonPlayRepo.save(pokemonPlay);
        return PokemonPlayMapper.mapToPokemonPlayDto(updatedPokemonObj);
    }




}
