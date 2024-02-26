package nl.novi.backendjavapokemonplay.service.impl;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.entity.PokemonPlay;
import nl.novi.backendjavapokemonplay.entity.Textlines;
import nl.novi.backendjavapokemonplay.exception.ResourceNotFoundException;
import nl.novi.backendjavapokemonplay.mapper.PokemonPlayMapper;
import nl.novi.backendjavapokemonplay.mapper.TextlinesMapper;
import nl.novi.backendjavapokemonplay.repository.PokemonPlayRepo;
import nl.novi.backendjavapokemonplay.repository.TextlinesRepo;
import nl.novi.backendjavapokemonplay.service.PokemonPlayService;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static nl.novi.backendjavapokemonplay.mapper.TextlinesMapper.mapToTextlinesDto;

@Service
public class TextlinesServiceImpl implements TextlinesService {

    @Autowired
    private TextlinesRepo textlinesRepo;

    @Autowired
    private PokemonPlayRepo pokemonPlayRepo;

    @Autowired
    private PokemonPlayService pokemonPlayService;

    public TextlinesServiceImpl(TextlinesRepo textlinesRepo,
                                PokemonPlayRepo pokemonPlayRepo,
                                PokemonPlayService pokemonPlayService) {
        this.textlinesRepo = textlinesRepo;
        this.pokemonPlayRepo = pokemonPlayRepo;
        this.pokemonPlayService = pokemonPlayService;
    }

    public TextlinesRepo getTextlinesRepo() {
        return textlinesRepo;
    }

    public void setTextlinesRepo(TextlinesRepo textlinesRepo) {
        this.textlinesRepo = textlinesRepo;
    }

    public PokemonPlayRepo getPokemonPlayRepo() {
        return pokemonPlayRepo;
    }

    public void setPokemonPlayRepo(PokemonPlayRepo pokemonPlayRepo) {
        this.pokemonPlayRepo = pokemonPlayRepo;
    }

    public PokemonPlayService getPokemonPlayService() {
        return pokemonPlayService;
    }

    public void setPokemonPlayService(PokemonPlayService pokemonPlayService) {
        this.pokemonPlayService = pokemonPlayService;
    }

    @Override
    public TextlinesDto addTextline(TextlinesDto textlinesDto) {
        Textlines textlines = TextlinesMapper.mapToTextlines(textlinesDto);
        Textlines saveTextlines = textlinesRepo.save(textlines);
        return TextlinesMapper.mapToTextlinesDto(saveTextlines);

    }

    @Override
    public TextlinesDto getTextline(Long pokemonPlayid) {
        return null;
    }

    @Override
    public PokemonPlayDto getPokemonPlayByName(String pokemonPlayName) {
        PokemonPlay pokemonPlay1 = pokemonPlayRepo.findPokemonPlayByNamePlayerAEqualsIgnoreCase(pokemonPlayName);
        PokemonPlay pokemonPlay = pokemonPlayRepo.findById((pokemonPlay1.getId())).orElseThrow(
                () -> new ResourceNotFoundException("Pokemonplay does not exist with given id: " + pokemonPlayName)
        );
        return PokemonPlayMapper.mapToPokemonPlayDto(pokemonPlay);
    }

    @Override
    public List<TextlinesDto> getAllTextlinesByPokemon(PokemonPlay pokemonPlay) {
        List<Textlines> textlinesList = textlinesRepo.findAll();
        return mapTextlinesToDtoList(textlinesList);
    }

    public List<TextlinesDto> getAllTextlines() {
        List<Textlines> tlList = textlinesRepo.findAll();
        return mapTextlinesToDtoList(tlList);
    }


    @Override
    public List<TextlinesDto> mapTextlinesToDtoList(List<Textlines> textlines) {
        List<TextlinesDto> tlDtoList = new ArrayList<>();
        for (Textlines tl : textlines) {
            TextlinesDto dto = mapToTextlinesDto(tl);
            if (tl.getPokemonplay() != null) {
                dto.setPokemonplayDto(PokemonPlayMapper.mapToPokemonPlayDto(tl.getPokemonplay()));
            }
            tlDtoList.add(dto);
        }
        return tlDtoList;
    }


}
