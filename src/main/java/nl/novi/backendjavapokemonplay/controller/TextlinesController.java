package nl.novi.backendjavapokemonplay.controller;

import nl.novi.backendjavapokemonplay.dto.TextlinesDto;

import nl.novi.backendjavapokemonplay.entity.Textlines;
;
import nl.novi.backendjavapokemonplay.repository.TextlinesRepo;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping("api/pokemonplay/textlines")
public class TextlinesController {
    private final TextlinesService textlinesService;
    private final TextlinesRepo textlinesRepo;


    public TextlinesController(TextlinesService textlinesService, TextlinesRepo textlinesRepo) {
        this.textlinesService = textlinesService;
        this.textlinesRepo = textlinesRepo;
    }
    @GetMapping("/textlines")
    public ResponseEntity<List<TextlinesDto>>  getTextLines(Long id) {

       List<TextlinesDto> dtos = null;
        List<Textlines> tlList = new ArrayList<>();
        tlList = textlinesRepo.findAll();
        dtos = textlinesService.mapTextlinesToDtoList(tlList);
        return ResponseEntity.ok(dtos);
    }




}
