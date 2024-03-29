package nl.novi.backendjavapokemonplay.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectricPokemon extends Pokemon {

    private static final String type = "electric";
    private static final List<String> attacks = Arrays.asList("thunderPunch", "electroBall", "thunder", "voltTackle");

    private static List<String> outputlines = new ArrayList<>();

    public ElectricPokemon(String name, int level, int hp, String food, String sound) {
        super(name, hp, level, food, sound, type);


    }

    public String getType() {
        return type;
    }

    public List<String> getAttacks() {
        return attacks;
    }


    public Textlines thunderPunch(Pokemon name, Pokemon enemy) {

        outputlines.add(name.getName() + " hits " + enemy.getName() + " with a ThunderPunch!");
        switch (enemy.getType()) {
            case "grass":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;

            case "water":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "fire":
                outputlines.add(enemy.getName() + " loses 15 hp");
                enemy.setHp(enemy.getHp() - 15);
                break;

            case "electric":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;

        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");
        return new Textlines(outputlines);
    }

    public Textlines electroBall(Pokemon name, Pokemon enemy) {
        outputlines.add(name.getName() + " throws a ElectroBall on " + enemy.getName());
        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 25 hp");
                enemy.setHp(enemy.getHp() - 25);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 35 hp");
                enemy.setHp(enemy.getHp() - 35);
                break;

            case "electric":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;

        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");
        return new Textlines(outputlines);
    }

    public Textlines thunder(Pokemon name, Pokemon enemy) {
        outputlines.add(name.getName() + " hits " + enemy.getName() + " with Thunder");
        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 50 hp");
                enemy.setHp(enemy.getHp() - 50);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " gaines 10 hp");
                outputlines.add(name.getName() + " gaines 10 hp");
                enemy.setHp(enemy.getHp() + 10);
                name.setHp(name.getHp() + 10);

        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");
        outputlines.add(name.getName() + " has " + name.getHp() + " hp left");
        return new Textlines(outputlines);
    }

    public Textlines voltTackle(Pokemon name, Pokemon enemy) {
        outputlines.add(name.getName() + " uses a VoltTackle on " + enemy.getName());
        switch (enemy.getType()) {
            case "fire":
                outputlines.add(enemy.getName() + " loses 20 hp");
                enemy.setHp(enemy.getHp() - 20);
                break;
            case "water":
                outputlines.add(enemy.getName() + " loses 40 hp");
                enemy.setHp(enemy.getHp() - 40);
                break;
            case "grass":
                outputlines.add(enemy.getName() + " loses 30 hp");
                enemy.setHp(enemy.getHp() - 30);
                break;
            case "electric":
                outputlines.add(enemy.getName() + " loses 10 hp");
                enemy.setHp(enemy.getHp() - 10);
                break;
        }
        outputlines.add(enemy.getName() + " has " + enemy.getHp() + " hp left");
        return new Textlines(outputlines);
    }

}
