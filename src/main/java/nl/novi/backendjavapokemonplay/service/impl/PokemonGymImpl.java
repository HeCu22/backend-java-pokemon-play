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

        charizard.setHp(150);
        blastoise.setHp(110);
        venusaur.setHp(135);
        ditto.setHp(140);
        raichu.setHp(160);
        gyarados.setHp(180);
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
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
        PokemonTrainer playerA = new PokemonTrainer(player1.getName(), pokemons);

        return choosePokemon(playerA);
    }

    @Override
    public void printPokemon(List<Pokemon> pokemons) {

    }

    @Override
    public Textlines enterFight(PokemonPlayDto pokemonPlayDto) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(pokemonPlayDto.getNamePlayerA(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        Pokemon cardA = selectPokemon(pokemonPlayDto.getCardPlayerA(), playerA);
        Pokemon cardGymOwner = selectPokemon(pokemonPlayDto.getCardGymOwner(), gymowner);
        Textlines textlines;
        textlines = fightRound(playerA, gymowner, cardA, cardGymOwner);
        return textlines;
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
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(trainer.getName(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        final Pokemon cardA = selectPokemon(pokemon.getName(), playerA);
        Pokemon cardGymOwner = selectPokemon(gymPokemon.getName(), gymowner);

        boolean continueLoop = true;
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;
        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {
            outputlines.add(pokemon.getName() + " starts with " + pokemon.getHp() + " and " +
                    gymowner.getName() + " has " + gymPokemon.getHp());
            outputlines.add("Its " + owner.getName() + "'s turn to attack");
            textlinesToReturn = gymOwnerAttacks(cardGymOwner, cardA);
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
        Textlines textlinesToReturn;

        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {
            textlinesToReturn = chooseAttackPlayer(pokemon);
            outputlines.addAll(textlinesToReturn.getTextlines());
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
        Textlines textlinesToReturn;
        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {
            outputlines.add(pokemon.getName() + " starts with " + pokemon.getHp() + " and " +
                    gymPokemon.getName() + " has " + gymPokemon.getHp());
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
    public Textlines performAttackPlayer(Pokemon playPokemon, Pokemon gymPokemon, String attack) {
        FirePokemon fire;
        ElectricPokemon electric;
        GrassPokemon grass;
        WaterPokemon water;

        String choosenAttack = attack.toLowerCase(Locale.ROOT);


        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;
        switch (playPokemon.getType()) {
            case "fire": {
                fire = new FirePokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "inferno":
                        textlinesToReturn = fire.inferno(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "pyroball":
                        textlinesToReturn = fire.pyroBall(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "firelash":
                        textlinesToReturn = fire.fireLash(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = fire.flameThrower(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            case "water": {
                water = new WaterPokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "surf":
                        textlinesToReturn = water.surf(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "hydropump":
                        textlinesToReturn = water.hydroPump(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "hydrocanon":
                        textlinesToReturn = water.hydroCanon(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = water.rainDance(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            case "grass": {
                grass = new GrassPokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "leafstorm":
                        textlinesToReturn = grass.leafStorm(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "solarbeam":
                        textlinesToReturn = grass.solarBeam(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "leechseed":
                        textlinesToReturn = grass.leechSeed(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:

                        textlinesToReturn = grass.leaveBlade(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
                break;
            }
            default: {
                electric = new ElectricPokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "thunderpunch":
                        textlinesToReturn = electric.thunderPunch(playPokemon, gymPokemon);

                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "electroball":
                        textlinesToReturn = electric.electroBall(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    case "thunder":
                        textlinesToReturn = electric.thunder(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                        break;
                    default:
                        textlinesToReturn = electric.voltTackle(playPokemon, gymPokemon);
                        outputlines.addAll(textlinesToReturn.getTextlines());
                }
            }
            break;
        }
        return new Textlines(outputlines);
    }

    @Override
    public Textlines gymOwnerAttacks(Pokemon gymPokemon, Pokemon playPokemon) {
        FirePokemon fire;
        ElectricPokemon electric;
        GrassPokemon grass;
        WaterPokemon water;

        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;
        int hp = playPokemon.getHp();
//        switch (playPokemon.getType()) {
//            case "fire": {
//                Pokemon pokemonsave = new FirePokemon(playPokemon.getName(), playPokemon.getLevel(), hp, playPokemon.getFood(), playPokemon.getSound());
//                break;
//            }
//            case "water": {
//                Pokemon pokemonsave = new WaterPokemon(playPokemon.getName(), playPokemon.getLevel(), hp, playPokemon.getFood(), playPokemon.getSound());
//                break;
//            }
//            case "electric": {
//                Pokemon pokemonsave = new ElectricPokemon(playPokemon.getName(), playPokemon.getLevel(), hp, playPokemon.getFood(), playPokemon.getSound());
//                break;
//            }
//            default:
//                Pokemon pokemonsave = new GrassPokemon(playPokemon.getName(), playPokemon.getLevel(), hp, playPokemon.getFood(), playPokemon.getSound());
//
//        }


        switch (gymPokemon.getType()) {
            case "fire": {
                fire = new FirePokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());

                List<String> attacks = fire.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                textlinesToReturn = performAttackPlayer(gymPokemon, playPokemon, attack);
                outputlines.addAll(textlinesToReturn.getTextlines());
                break;
//            /* */   switch (attack) {
                //      case "inferno":
                //        textlinesToReturn = fire.inferno(gymPokemon, playPokemon);
                //      outputlines.addAll(textlinesToReturn.getTextlines());
//                break;
                //              case "pyroBall":
                //                textlinesToReturn = fire.pyroBall(gymPokemon, playPokemon);
                //              outputlines.addAll(textlinesToReturn.getTextlines());
                //            break;
//                case "fireLash":
                //                  textlinesToReturn = fire.fireLash(gymPokemon, playPokemon);
                //                outputlines.addAll(textlinesToReturn.getTextlines());
                //              break;
                //        default:
                //          textlinesToReturn = fire.flameThrower(gymPokemon, playPokemon);
                //        outputlines.addAll(textlinesToReturn.getTextlines());
//            }
                //          break;
            }
            case "water": {
                water = new WaterPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                List<String> attacks = water.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                textlinesToReturn = performAttackPlayer(gymPokemon, playPokemon, attack);
                outputlines.addAll(textlinesToReturn.getTextlines());
                break;
//                switch (attack) {
//                    case "surf":
//                        textlinesToReturn = water.surf(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    case "hydroPump":
//                        textlinesToReturn = water.hydroPump(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    case "hydroCanon":
//                        textlinesToReturn = water.hydroCanon(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    default:
//                        textlinesToReturn = water.rainDance(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                }
//                break;
            }
            case "grass": {
                grass = new GrassPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                List<String> attacks = grass.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                textlinesToReturn = performAttackPlayer(gymPokemon, playPokemon, attack);
                outputlines.addAll(textlinesToReturn.getTextlines());
                break;
//                switch (attack) {
//                    case "leafStorm":
//                        textlinesToReturn = grass.leafStorm(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    case "solarBeam":
//                        textlinesToReturn = grass.solarBeam(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    case "leechSeed":
//                        textlinesToReturn = grass.leechSeed(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    default:
//                        textlinesToReturn = grass.leaveBlade(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                }
//                break;
            }
            default: {
                electric = new ElectricPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                List<String> attacks = electric.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                textlinesToReturn = performAttackPlayer(gymPokemon, playPokemon, attack);
                outputlines.addAll(textlinesToReturn.getTextlines());
                break;
//                switch (attack) {
//                    case "thunderPunch":
//                        textlinesToReturn = electric.thunderPunch(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    case "electroBall":
//                        textlinesToReturn = electric.electroBall(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    case "thunder":
//                        textlinesToReturn = electric.thunder(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
//                        break;
//                    default:
//                        textlinesToReturn = electric.voltTackle(gymPokemon, playPokemon);
//                        outputlines.addAll(textlinesToReturn.getTextlines());
            }
        }

        return new Textlines(outputlines);
    }


    @Override
    public Textlines attackOrChange(String choice, Pokemon pokemon, Pokemon gymPokemon, PokemonTrainer trainer, PokemonGymOwner gym) {
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;

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
