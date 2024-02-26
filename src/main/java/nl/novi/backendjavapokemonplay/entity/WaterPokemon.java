package nl.novi.backendjavapokemonplay.entity;

import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.mapper.TextlinesMapper;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterPokemon extends Pokemon {
    private static final String type = "water";

    private static final List<String> attacks = Arrays.asList("surf", "hydroPump", "hydroCanon", "rainDance");

    private static List<String> outputlines = new ArrayList<>();

    private TextlinesService textlinesService;


    public WaterPokemon(String name, int level, int hp, String food, String sound) {
        super(name, level, hp, food, sound, type);
        this.textlinesService = textlinesService;
    }

    public String getType() {
        return type;
    }

    public List<String> getAttacks() {
        return attacks;
    }


    public List<String> surf(Pokemon name, Pokemon enemy) {
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " performed surf attack on " + enemy.getName());
        switch (enemy.getType()) {
            case "grass":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;

            case "fire":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;

            case "electric":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;

            case "water":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;

        }

        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");

//        for (int i = 0; i < outputlines.size(); i++) {
//            Textlines newtextlines = new Textlines();
//            newtextlines.setTextline(outputlines.get(i));
//            TextlinesDto textlinesDto = textlinesService.addTextline(TextlinesMapper.mapToTextlinesDto(newtextlines));
//        }
        return outputlines;
    }

    public List<String> hydroPump(Pokemon name, Pokemon enemy) {
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " performed hydro-pump on " + enemy.getName());
        switch (enemy.getType()) {
            case "grass":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;

            case "fire":
                outputlines.add(enemy.getName() + " loses 45 hp");
                enemy.setHp(enemy.getHp() - 45);
                break;

            case "electric":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;

            case "water":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;

        }

        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");

        return outputlines;

    }

    public List<String> hydroCanon(Pokemon name, Pokemon enemy) {

        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " performed hydro-canon on " + enemy.getName());
        switch (enemy.getType()) {
            case "grass":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;

            case "fire":
                outputlines.add(enemy.getName() + " loses 50 hp");
                enemy.setHp(enemy.getHp() - 50);
                break;

            case "electric":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;

            case "water":
                outputlines.add(enemy.getName() + " loses 15 hp");
                enemy.setHp(enemy.getHp() - 15);
                break;

        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");
        return outputlines;

    }

    public List<String> rainDance(Pokemon name, Pokemon enemy) {

        if (!outputlines.isEmpty()) {
            outputlines.removeAll(outputlines);
        }

        outputlines.add(name.getName() + " performed rain-dance " + enemy.getName());
        switch (enemy.getType()) {
            case "water":
                outputlines.add(enemy.getName() + " gains 20 hp");
                enemy.setHp(enemy.getHp() + 20);
                break;


            case "fire":
                outputlines.add(enemy.getName() + " loses 5 hp");
                enemy.setHp(enemy.getHp() - 5);
                break;

            case "electric":
                outputlines.add("has no effect on " + enemy.getName());
                break;

            case "grass":
                outputlines.add(enemy.getName() + " gains 10 hp");
                enemy.setHp(enemy.getHp() + 10);
                break;

        }

        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");

        return outputlines;


    }

}
