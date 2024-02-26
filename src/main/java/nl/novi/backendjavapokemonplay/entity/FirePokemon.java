package nl.novi.backendjavapokemonplay.entity;

import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.mapper.TextlinesMapper;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import nl.novi.backendjavapokemonplay.service.impl.TextlinesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirePokemon extends Pokemon {

    private static final String type = "fire";

    private static final List<String> attacks = Arrays.asList("inferno", "pyroBall", "fireLash", "flameThrower");

    private static List<String> outputlines = new ArrayList<>();


    private TextlinesService textlinesService;

    public FirePokemon(String name, int level, int hp, String food, String sound) {
        super(name, level, hp, food, sound, type);
        this.textlinesService = textlinesService;

    }

    public String getType() {
        return type;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public List<String> inferno(Pokemon name, Pokemon enemy) {
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " performed Inferno on " + enemy.getName());

        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 15 hp");
                enemy.setHp(enemy.getHp() - 15);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 50 hp");
                enemy.setHp(enemy.getHp() - 50);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 45 hp");
                enemy.setHp(enemy.getHp() - 45);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 25 hp");
                enemy.setHp(enemy.getHp() - 25);
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

    public List<String> pyroBall(Pokemon name, Pokemon enemy) {
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " throws a PyroBall on " + enemy.getName());
        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 5 hp");
                enemy.setHp(enemy.getHp() - 5);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 45 hp");
                enemy.setHp(enemy.getHp() - 45);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 35 hp");
                enemy.setHp(enemy.getHp() - 35);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
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

    public List<String> fireLash(Pokemon name, Pokemon enemy) {
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " hits " + enemy.getName() + " with FireLash");
        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 25 hp");
                enemy.setHp(enemy.getHp() - 25);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;
        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");

        return outputlines;

    }

    public List<String> flameThrower(Pokemon name, Pokemon enemy) {
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " hits " + enemy.getName() + " with FlameThrower");
        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;

            case "electric":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
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


}


