package nl.novi.backendjavapokemonplay.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirePokemon extends Pokemon {

    private static final String type = "fire";

    private static final List<String> attacks = Arrays.asList("inferno", "pyroBall", "fireLash", "flameThrower");

    private static  List<String> outputlines = new ArrayList<>();

    public FirePokemon(String name, int level, int hp, String food, String sound) {
        super(name, hp, level, food, sound, type);

    }

    public String getType() {
        return type;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public Textlines inferno(Pokemon name, Pokemon enemy) {
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
        return new Textlines(outputlines);
    }

    public Textlines pyroBall(Pokemon name, Pokemon enemy) {
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
        return new Textlines(outputlines);
    }

    public Textlines fireLash(Pokemon name, Pokemon enemy) {
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
        return new Textlines(outputlines);
    }

    public Textlines flameThrower(Pokemon name, Pokemon enemy) {
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
        return new Textlines(outputlines);
    }


}


