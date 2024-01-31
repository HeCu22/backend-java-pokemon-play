package nl.novi.backendjavapokemonplay.service.impl;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.entity.*;
import nl.novi.backendjavapokemonplay.mapper.PokemonPlayMapper;
import nl.novi.backendjavapokemonplay.service.PokemonGym;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
// Los in deze klasse alle foutmeldingen op door (abstracte) klassen met variabelen en methodes te maken en een interface met methodes (en soms een import).
public class PokemonGymImpl implements PokemonGym {


    private static final FirePokemon charizard = new FirePokemon("Charizard", 76, 150, "firenougats", "GRRRRRRRRRRRRRRR");

    private static final WaterPokemon blastoise = new WaterPokemon("Blastoise", 40, 110, "Pokeflakes", "Blaaaaasssssstooooiiiiissss");
    private static final GrassPokemon venusaur = new GrassPokemon("Venusaur", 50, 135, "Pokeleafs", "Veeeeeeeeennnnnuuuuuusaur");
    private static final GrassPokemon ditto = new GrassPokemon("Ditto", 60, 140, "Everything", "Dittto diiiito ");
    private static final ElectricPokemon raichu = new ElectricPokemon("Raichu", 80, 160, "Pokebrocks", "Raaaaiiiiicccchhhhuuuuuuu!!!!");
    private static final WaterPokemon gyarados = new WaterPokemon("Gyarados", 90, 180, "Pokeflakes", "Gyaaaaaaaaarrrraaaadoooos");

    private static List<Pokemon> pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);

    String choice = "";

    public PokemonGymImpl(List<Pokemon> pokemons) {
        PokemonGymImpl.pokemons = pokemons;
    }


    @Override
    public PokemonPlayDto enterTheGym(PokemonTrainer player1) {

        charizard.setHp(150); blastoise.setHp(110);venusaur.setHp(135); ditto.setHp(140); raichu.setHp(160); gyarados.setHp(180);
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonGymImpl pokemonGym = new PokemonGymImpl(pokemons);
        PokemonGymOwner gymOwner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        Pokemon gymPokemon = chooseGymPokemon(gymOwner);
        PokemonPlay pokemonplayentered = new PokemonPlay();
        pokemonplayentered.setNamePlayerA(player1.getName());
        pokemonplayentered.setNameGymOwner(gymOwner.getName());
        pokemonplayentered.setCardGymOwner(gymPokemon.getName());
        return PokemonPlayMapper.mapToPokemonPlayDto(pokemonplayentered);



    }

    @Override
    public List<Pokemon> enterCard(PokemonTrainer player1) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonGymImpl pokemonGym = new PokemonGymImpl(pokemons);
        PokemonTrainer playerA = new PokemonTrainer(player1.getName(), pokemons);

        return choosePokemon(playerA);
    }

    @Override
    public Textlines enterFight(PokemonPlayDto pokemonPlayDto) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(pokemonPlayDto.getNamePlayerA(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        Pokemon cardA = selectPokemon(pokemonPlayDto.getCardPlayerA(), playerA);
        Pokemon cardGymOwner = selectPokemon(pokemonPlayDto.getCardGymOwner(), gymowner);
        Textlines  textlines = null;
        textlines = fightRound(playerA, gymowner, cardA, cardGymOwner);
        return textlines;
    }


    @Override
    public void printPokemon(List<Pokemon> pokemons) {
        List<String> outputlines = new ArrayList<>();
        for (Pokemon p : pokemons) {
            outputlines.add(p.getName());
        }
    }

    @Override
    public Pokemon selectPokemon(String pokemon, PokemonTrainer trainer) {
        List<Pokemon> pokemons = trainer.getPokemons();
        int number = 0;
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getName().equalsIgnoreCase(pokemon)) {
                number = i;
            }
        }
        return pokemons.get(number);
    }

    @Override
    public Textlines fightRound(PokemonTrainer trainer, PokemonGymOwner owner, Pokemon pokemon, Pokemon gymPokemon) {
        boolean continueLoop = true;
        List<String> outputlines = new ArrayList<>();
        Textlines  textlinesToReturn = null;
        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {
            outputlines.add("Its " + owner.getName() + "'s turn to attack");

            textlinesToReturn = gymOwnerAttacks(gymPokemon, pokemon);
            outputlines.addAll(textlinesToReturn.getTextlines());
            outputlines.add("Its " + trainer.getName() + "'s turn to attack");

            outputlines.add("Do you want to attack or change your pokemon?");
            outputlines.add("Type a for attack or c for change");
            continueLoop = false;


        }

        if (continueLoop) {
            if (pokemon.getHp() <= 0) {

                outputlines.add(gymPokemon.getName() + " has defeated " + pokemon.getName());
            } else if (gymPokemon.getHp() <= 0) {

                outputlines.add(pokemon.getName() + " has defeated " + gymPokemon.getName());
            }

            outputlines.add("Would you like to keep playing?");
            outputlines.add("enter yes or no");
        }

        return new Textlines(outputlines);
    }


    @Override
    public Textlines fightRoundNext(PokemonPlayDto pokemonPlayDto) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(pokemonPlayDto.getNamePlayerA(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        Pokemon pokemon = selectPokemon(pokemonPlayDto.getCardPlayerA(), playerA);
        Pokemon gymPokemon = selectPokemon(pokemonPlayDto.getCardGymOwner(), gymowner);

        boolean continueLoop = true;
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn = null;


        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {
            textlinesToReturn = chooseAttackPlayer(pokemon);
            outputlines.addAll(textlinesToReturn.getTextlines());
            //         String attack = outputlines.get(0);
//            textlinesToReturn = performAttackPlayer(pokemon, gymPokemon, attack);
//            outputlines.addAll(textlinesToReturn.getTextlines());
            continueLoop = false;
        }
        if (continueLoop) {
            if (pokemon.getHp() <= 0) {

                outputlines.add(gymPokemon.getName() + " has defeated " + pokemon.getName());
            } else if (gymPokemon.getHp() <= 0) {

                outputlines.add(pokemon.getName() + " has defeated " + gymPokemon.getName());
            }

            outputlines.add("Would you like to keep playing?");
            outputlines.add("enter yes or no");
        }

        //      return fightRound(playerA, gymowner, pokemon, gymPokemon);

        return new Textlines(outputlines);
    }

    @Override
    public Textlines performAttack(String attack, PokemonPlayDto pokemonPlayDto) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(pokemonPlayDto.getNamePlayerA(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        Pokemon pokemon = selectPokemon(pokemonPlayDto.getCardPlayerA(), playerA);
        Pokemon gymPokemon = selectPokemon(pokemonPlayDto.getCardGymOwner(), gymowner);

        boolean continueLoop = true;
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn = null;
        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {

            textlinesToReturn = performAttackPlayer(pokemon, gymPokemon, attack);
            outputlines.addAll(textlinesToReturn.getTextlines());

            outputlines.add("Its " + gymowner.getName() + "'s turn to attack");
            textlinesToReturn = gymOwnerAttacks(gymPokemon, pokemon);
            outputlines.addAll(textlinesToReturn.getTextlines());

            continueLoop = false;
        }


        if (pokemon.getHp() <= 0) {

            outputlines.add(gymPokemon.getName() + " has defeated " + pokemon.getName());
        } else if (gymPokemon.getHp() <= 0) {

            outputlines.add(pokemon.getName() + " has defeated " + gymPokemon.getName());
        }
        outputlines.add("Its " + playerA.getName() + "'s turn to attack");
        outputlines.add("Do you want to attack or change your pokemon?");
        outputlines.add("Type a for attack or c for change");

        return new Textlines(outputlines);
    }


    @Override
    public Pokemon chooseGymPokemon(PokemonGymOwner gymOwner) {
        Random rand = new Random();
        List<Pokemon> pokemons = new ArrayList<>();
        for (Pokemon p : gymOwner.getPokemons()) {
            if (p.getHp() > 0) {
                pokemons.add(p);
            }
        }
        int amountOfPokemons = pokemons.size();
        int randomNumber = rand.nextInt(amountOfPokemons);
        return pokemons.get(randomNumber);
    }

    @Override
    public List<Pokemon> choosePokemon(PokemonTrainer trainer) {

        List<Pokemon> pokemons = new ArrayList<>();
        for (Pokemon p : trainer.getPokemons()) {
            if (p.getHp() > 0) {
                pokemons.add(p);
            }
        }


        return pokemons;
    }

    @Override
    public int randomAttackByGymOwner() {
        Random rand = new Random();
        int maxAttacks = 4;
        return rand.nextInt(maxAttacks);
    }

    @Override
    public Textlines chooseAttackPlayer(Pokemon p) {
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn = null;
        String type = p.getType();
        switch (type) {
            case "fire": {
                FirePokemon fp = (FirePokemon) p;
                outputlines.addAll(fp.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                return new Textlines(outputlines);
            }
            case "water": {
                WaterPokemon wp = (WaterPokemon) p;
                outputlines.addAll(wp.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                return new Textlines(outputlines);
            }
            case "electric": {
                ElectricPokemon ep = (ElectricPokemon) p;

                outputlines.addAll(ep.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                return new Textlines(outputlines);
            }
            default: {
                GrassPokemon gp = (GrassPokemon) p;
                outputlines.addAll(gp.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                return new Textlines(outputlines);

            }
        }

    }

    @Override
    public Textlines performAttackPlayer(Pokemon pokemon, Pokemon gymPokemon, String attack) {
        FirePokemon fire;
        ElectricPokemon electric;
        GrassPokemon grass;
        WaterPokemon water;

        String choosenAttack = attack.toLowerCase(Locale.ROOT);

        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn = null;
        switch (pokemon.getType()) {
            case "fire": {
                fire = new FirePokemon(pokemon.getName(), pokemon.getLevel(), pokemon.getHp(), pokemon.getFood(), pokemon.getSound());
                switch (choosenAttack) {
                    case "inferno":
                        textlinesToReturn = fire.inferno(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "pyroball":
                        textlinesToReturn = fire.pyroBall(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "firelash":
                        textlinesToReturn = fire.fireLash(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = fire.flameThrower(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            case "water": {
                water = new WaterPokemon(pokemon.getName(), pokemon.getLevel(), pokemon.getHp(), pokemon.getFood(), pokemon.getSound());
                switch (choosenAttack) {
                    case "surf":
                        textlinesToReturn = water.surf(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "hydropump":
                        textlinesToReturn = water.hydroPump(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "hydrocanon":
                        textlinesToReturn = water.hydroCanon(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = water.rainDance(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            case "grass": {
                grass = new GrassPokemon(pokemon.getName(), pokemon.getLevel(), pokemon.getHp(), pokemon.getFood(), pokemon.getSound());
                switch (choosenAttack) {
                    case "leafstorm":
                        textlinesToReturn = grass.leafStorm(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "solarbeam":
                        textlinesToReturn = grass.solarBeam(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "leechseed":
                        textlinesToReturn = grass.leechSeed(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:

                        textlinesToReturn = grass.leaveBlade(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            default: {
                electric = new ElectricPokemon(pokemon.getName(), pokemon.getLevel(), pokemon.getHp(), pokemon.getFood(), pokemon.getSound());
                switch (choosenAttack) {
                    case "thunderpunch":
                        textlinesToReturn = electric.thunderPunch(pokemon, gymPokemon);

                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "electroball":
                        textlinesToReturn = electric.electroBall(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "thunder":
                        textlinesToReturn = electric.thunder(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = electric.voltTackle(pokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
            }
            break;
        }
        return new Textlines(outputlines);
    }

    @Override
    public Textlines gymOwnerAttacks(Pokemon gymPokemon, Pokemon pokemon) {
        FirePokemon fire;
        ElectricPokemon electric;
        GrassPokemon grass;
        WaterPokemon water;

        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn = null;
        Pokemon pokemonsave = pokemon;

        switch (gymPokemon.getType()) {
            case "fire": {
                fire = new FirePokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());

                List<String> attacks = fire.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                switch (attack) {
                    case "inferno":
                        textlinesToReturn = fire.inferno(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "pyroBall":
                        textlinesToReturn = fire.pyroBall(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "fireLash":
                        textlinesToReturn = fire.fireLash(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = fire.flameThrower(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            case "water": {
                water = new WaterPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                String attack = water.getAttacks().get(randomAttackByGymOwner());
//                Textlines textlinesToReturn;
                switch (attack) {
                    case "surf":
                        textlinesToReturn = water.surf(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "hydroPump":
                        textlinesToReturn = water.hydroPump(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "hydroCanon":
                        textlinesToReturn = water.hydroCanon(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = water.rainDance(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            case "grass": {
                grass = new GrassPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                String attack = grass.getAttacks().get(randomAttackByGymOwner());
                //      Textlines textlinesToReturn;
                switch (attack) {
                    case "leafStorm":
                        textlinesToReturn = grass.leafStorm(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "solarBeam":
                        textlinesToReturn = grass.solarBeam(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "leechSeed":
                        textlinesToReturn = grass.leechSeed(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = grass.leaveBlade(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            default: {
                electric = new ElectricPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                //      Textlines textlinesToReturn;
                String attack = electric.getAttacks().get(randomAttackByGymOwner());
                switch (attack) {
                    case "thunderPunch":
                        textlinesToReturn = electric.thunderPunch(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "electroBall":
                        textlinesToReturn = electric.electroBall(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "thunder":
                        textlinesToReturn = electric.thunder(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = electric.voltTackle(gymPokemon, pokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
            }
        }
        return new Textlines(outputlines);
    }


    @Override
    public Textlines attackOrChange(String choice, Pokemon pokemon, Pokemon gymPokemon, PokemonTrainer trainer, PokemonGymOwner gym) {
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn = null;

        outputlines.add("Do you want to attack or change your pokemon?");
        outputlines.add("Type a for attack or c for change");
//        String choice = speler_A.nextLine();

        if (choice.equalsIgnoreCase("a")) {
            textlinesToReturn = chooseAttackPlayer(pokemon);
            outputlines.addAll(textlinesToReturn.getTextlines());
            String attack = outputlines.get(0);
            textlinesToReturn = performAttackPlayer(pokemon, gymPokemon, attack);
            outputlines.addAll(textlinesToReturn.getTextlines());

        } else {
            List<Pokemon> pokemonList = choosePokemon(trainer);
            pokemon = pokemonList.get(0);
            //         attackOrChange(pokemon, gymPokemon, trainer, gym);
            textlinesToReturn = fightRound(trainer, gym, pokemon, gymPokemon);
            outputlines.addAll(textlinesToReturn.getTextlines());
        }
        return new Textlines(outputlines);
    }

}
