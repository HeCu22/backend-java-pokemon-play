package nl.novi.backendjavapokemonplay.entity;

import jakarta.persistence.Entity;
import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.mapper.TextlinesMapper;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import nl.novi.backendjavapokemonplay.service.impl.TextlinesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GrassPokemon extends Pokemon {


    private static final String type = "grass";

    private static final List<String> attacks = Arrays.asList("leafStorm", "solarBeam", "leechSeed", "leaveBlade");

    private static List<String> outputlines = new ArrayList<>();


    private TextlinesService textlinesService;


    public  GrassPokemon(String name, int level, int hp, String food, String sound) {
        super(name, level, hp, food, sound, type);


    }

    public String getType() {
        return type;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public List<String> leafStorm(Pokemon name, Pokemon enemy) {
//        List<String> outputlines = new ArrayList<>();
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " performed LeafStorm " + enemy.getName());
        switch (enemy.getType()) {
            case "water":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "fire":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;
            case "grass":
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

    public List<String> solarBeam(Pokemon name, Pokemon enemy) {
//        List<String> outputlines = new ArrayList<>();
        outputlines.removeAll(outputlines);

        outputlines.add(name.getName() + " performed a SolarBeam on " + enemy.getName());
        switch (enemy.getType()) {
            case "water":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "fire":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 5 hp");
                enemy.setHp(enemy.getHp() - 5);
                break;
        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");

        return outputlines;
    }


    public List<String> leechSeed(Pokemon name, Pokemon enemy) {
        //    List<String> outputlines = new ArrayList<>();
        outputlines.removeAll(outputlines);
        outputlines.add(name.getName() + " performed a LeechSeed on " + enemy.getName());
        switch (enemy.getType()) {
            case "water":
                outputlines.add(enemy.getName() + " loses 10 hp");
                outputlines.add(name.getName() + " gaines 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                name.setHp(name.getHp() + 10);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 50 hp");
                outputlines.add(name.getName() + " gaines 50 hp");
                enemy.setHp(enemy.getHp() - 50);
                name.setHp(name.getHp() + 50);
                break;
            case "fire":
                outputlines.add(enemy.getName() + " loses 20 hp");
                outputlines.add(name.getName() + " gaines 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                name.setHp(name.getHp() + 20);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 5 hp");
                outputlines.add(name.getName() + " gaines 5 hp");
                enemy.setHp(enemy.getHp() - 5);
                name.setHp(name.getHp() + 5);
                break;
        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");
        outputlines.add(name.getName() + " has " + name.getHp() + " hp left");

        return outputlines;
    }

    public List<String> leaveBlade(Pokemon name, Pokemon enemy) {
        //   List<String> outputlines = new ArrayList<>();
        outputlines.removeAll(outputlines);
        outputlines.add(name.getName() + " performed a LeaveBlade on " + enemy.getName());
        switch (enemy.getType()) {
            case "water":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 50 hp");
                enemy.setHp(enemy.getHp() - 50);
                break;
            case "fire":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 5 hp");
                enemy.setHp(enemy.getHp() - 5);
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
