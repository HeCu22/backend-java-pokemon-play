package nl.novi.backendjavapokemonplay.service.impl;

import nl.novi.backendjavapokemonplay.dto.PokemonPlayDto;
import nl.novi.backendjavapokemonplay.dto.TextlinesDto;
import nl.novi.backendjavapokemonplay.entity.*;

import nl.novi.backendjavapokemonplay.mapper.PokemonPlayMapper;
import nl.novi.backendjavapokemonplay.mapper.TextlinesMapper;
import nl.novi.backendjavapokemonplay.repository.PokemonPlayRepo;
import nl.novi.backendjavapokemonplay.repository.TextlinesRepo;
import nl.novi.backendjavapokemonplay.service.PokemonGym;

import nl.novi.backendjavapokemonplay.service.PokemonPlayService;
import nl.novi.backendjavapokemonplay.service.TextlinesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TextlinesRepo textlinesRepo;

    @Autowired
    private TextlinesService textlinesService;
    private PokemonPlayService pokemonPlayService;
    private PokemonPlayRepo pokemonPlayRepo;
    private PokemonGymImpl pokemonGym;

    public PokemonGymImpl(List<Pokemon> pokemons,
                          PokemonPlayRepo pokemonPlayRepo,
                          PokemonPlayService pokemonPlayService,
                          TextlinesRepo textlinesRepo,
                          TextlinesService textlinesService) {

        PokemonGymImpl.pokemons = pokemons;
        this.pokemonPlayRepo = pokemonPlayRepo;
        this.pokemonPlayService = pokemonPlayService;
        this.textlinesRepo = textlinesRepo;
        this.textlinesService = textlinesService;
    }

    public PokemonPlayService getPokemonPlayService() {
        return pokemonPlayService;
    }

    public PokemonPlayRepo getPokemonPlayRepo() {
        return pokemonPlayRepo;
    }

    public TextlinesRepo getTextlinesRepo() {
        return textlinesRepo;
    }

    public TextlinesService getTextlinesService() {
        return textlinesService;
    }

    public PokemonGymImpl() {
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
        Textlines newtextlines = new Textlines();
        PokemonPlayDto dto = new PokemonPlayDto();
        String playerName = player1.getName();
        dto = textlinesService.getPokemonPlayByName(playerName);
        dto.setNamePlayerA(playerName);
        dto.setNameGymOwner(gymOwner.getName());
        dto.setCardGymOwner(gymPokemon.getName());

        return dto;


    }

    @Override
    public List<Pokemon> enterCard(PokemonTrainer player1) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(player1.getName(), pokemons);

        return choosePokemon(playerA);
    }

    ;

    @Override
    public void printPokemon(List<Pokemon> pokemons) {

    }

    ;

    @Override
    public void enterFight(PokemonPlayDto pokemonPlayDto) {

        fightRound(pokemonPlayDto);
    }

    ;
//

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
    public void fightRound(PokemonPlayDto pokemonPlayDto) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);

        PokemonTrainer playerA = new PokemonTrainer(pokemonPlayDto.getNamePlayerA(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        final Pokemon cardA = selectPokemon(pokemonPlayDto.getCardPlayerA(), playerA);
        Pokemon cardGymOwner = selectPokemon(pokemonPlayDto.getCardGymOwner(), gymowner);

        boolean continueLoop = true;
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;
        while (continueLoop && cardA.getHp() > 0 && cardGymOwner.getHp() > 0) {
            outputlines.add(cardA.getName() + " starts with " + cardA.getHp() + " and " +
                    gymowner.getName() + " has " + cardGymOwner.getHp());
            outputlines.add("Its " + gymowner.getName() + "'s turn to attack");
            outputlines.addAll(gymOwnerAttacks(cardGymOwner, cardA));

            outputlines.add("Its " + playerA.getName() + "'s turn to attack");
            outputlines.add("Attack or change your pokemon?");
            outputlines.add("Type a for attack or c for change");
            continueLoop = false;
        }
        if (continueLoop) {
            if (cardA.getHp() <= 0) {
                outputlines.add(cardGymOwner.getName() + " has defeated " + cardA.getName());
            } else if (cardGymOwner.getHp() <= 0) {
                outputlines.add(cardA.getName() + " has defeated " + cardGymOwner.getName());
            }
            outputlines.add("Would you like to keep playing?");
            outputlines.add("enter yes or no");
        }
        PokemonPlayDto dto = new PokemonPlayDto();
        dto = textlinesService.getPokemonPlayByName(playerA.getName());
        for (int i = 0; i < outputlines.size(); i++) {
            Textlines newtextlines = new Textlines();
            newtextlines.setTextline(outputlines.get(i));
            newtextlines.pokemonplay = PokemonPlayMapper.mapToPokemonPlay(dto);
            TextlinesDto textlinesDto = textlinesService.addTextline(TextlinesMapper.mapToTextlinesDto(newtextlines));
        }
    }


    @Override
    public void fightRoundNext(PokemonPlayDto pokemonPlayDto) {
        pokemons = Arrays.asList(charizard, blastoise, venusaur, ditto, raichu, gyarados);
        PokemonTrainer playerA = new PokemonTrainer(pokemonPlayDto.getNamePlayerA(), pokemons);
        PokemonGymOwner gymowner = new PokemonGymOwner("Brock", "Pewter City", pokemons);
        Pokemon pokemon = selectPokemon(pokemonPlayDto.getCardPlayerA(), playerA);
        Pokemon gymPokemon = selectPokemon(pokemonPlayDto.getCardGymOwner(), gymowner);
        boolean continueLoop = true;
        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;

        while (continueLoop && pokemon.getHp() > 0 && gymPokemon.getHp() > 0) {
            outputlines.addAll(chooseAttackPlayer(pokemon));

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


        PokemonPlayDto dto = new PokemonPlayDto();
        dto = textlinesService.getPokemonPlayByName(playerA.getName());
        for (int i = 0; i < outputlines.size(); i++) {
            Textlines newtextlines = new Textlines();
            newtextlines.setTextline(outputlines.get(i));
            newtextlines.pokemonplay = PokemonPlayMapper.mapToPokemonPlay(dto);
            TextlinesDto textlinesDto = textlinesService.addTextline(TextlinesMapper.mapToTextlinesDto(newtextlines));
        }

    }

    @Override
    public void performAttack(String attack, PokemonPlayDto pokemonPlayDto) {
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
            outputlines = performAttackPlayer(pokemon, gymPokemon, attack);


            outputlines.add("Its " + gymowner.getName() + "'s turn to attack");
            outputlines.addAll(gymOwnerAttacks(gymPokemon, pokemon));


            continueLoop = false;
        }


        if (pokemon.getHp() <= 0) {

            outputlines.add(gymPokemon.getName() + " has defeated " + pokemon.getName());
        } else if (gymPokemon.getHp() <= 0) {

            outputlines.add(pokemon.getName() + " has defeated " + gymPokemon.getName());
        }
        outputlines.add("Its " + playerA.getName() + "'s turn to attack");
        outputlines.add("Attack or change your pokemon?");
        outputlines.add("Type a for attack or c for change");
        PokemonPlayDto dto = new PokemonPlayDto();
        dto = textlinesService.getPokemonPlayByName(playerA.getName());
        for (int i = 0; i < outputlines.size(); i++) {
            Textlines newtextlines = new Textlines();
            newtextlines.setTextline(outputlines.get(i));
            newtextlines.pokemonplay = PokemonPlayMapper.mapToPokemonPlay(dto);
            TextlinesDto textlinesDto = textlinesService.addTextline(TextlinesMapper.mapToTextlinesDto(newtextlines));
        }
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
    public List<String> chooseAttackPlayer(Pokemon p) {
        List<String> outputlines = new ArrayList<>();
        String type = p.getType();
        switch (type) {
            case "fire": {
                FirePokemon fp = (FirePokemon) p;
                outputlines.addAll(fp.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                break;
            }
            case "water": {
                WaterPokemon wp = (WaterPokemon) p;
                outputlines.addAll(wp.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                break;
            }
            case "electric": {
                ElectricPokemon ep = (ElectricPokemon) p;

                outputlines.addAll(ep.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");
                break;
            }
            default: {
                GrassPokemon gp = (GrassPokemon) p;
                outputlines.addAll(gp.getAttacks());
                outputlines.add("Choose your attack");
                outputlines.add("choose one of the values in list");


            }
        }

        return outputlines;
    }


    @Override
    public List<String> performAttackPlayer(Pokemon playPokemon, Pokemon gymPokemon, String attack) {
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
                        outputlines = fire.inferno(playPokemon, gymPokemon);
                        break;
                    case "pyroball":
                        outputlines = fire.pyroBall(playPokemon, gymPokemon);
                        break;
                    case "firelash":
                        outputlines = fire.fireLash(playPokemon, gymPokemon);
                        break;
                    default:
                        outputlines = fire.flameThrower(playPokemon, gymPokemon);
                }
                break;
            }
            case "water": {
                water = new WaterPokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "surf":
                        outputlines = water.surf(playPokemon, gymPokemon);
                        break;
                    case "hydropump":
                        outputlines = water.hydroPump(playPokemon, gymPokemon);

                        break;
                    case "hydrocanon":
                        outputlines = water.hydroCanon(playPokemon, gymPokemon);

                        break;
                    default:
                        outputlines = water.rainDance(playPokemon, gymPokemon);

                }
                break;
            }
            case "grass": {
                grass = new GrassPokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "leafstorm":
                        outputlines = grass.leafStorm(playPokemon, gymPokemon);

                        break;
                    case "solarbeam":
                        outputlines = grass.solarBeam(playPokemon, gymPokemon);

                        break;
                    case "leechseed":
                        outputlines = grass.leechSeed(playPokemon, gymPokemon);

                        break;
                    default:

                        outputlines = grass.leaveBlade(playPokemon, gymPokemon);

                }
                break;
            }
            default: {
                electric = new ElectricPokemon(playPokemon.getName(), playPokemon.getLevel(), playPokemon.getHp(), playPokemon.getFood(), playPokemon.getSound());
                switch (choosenAttack) {
                    case "thunderpunch":
                        outputlines = electric.thunderPunch(playPokemon, gymPokemon);

                        break;
                    case "electroball":
                        outputlines = electric.electroBall(playPokemon, gymPokemon);

                        break;
                    case "thunder":
                        outputlines = electric.thunder(playPokemon, gymPokemon);

                        break;
                    default:
                        outputlines = electric.voltTackle(playPokemon, gymPokemon);

                }
            }
            break;
        }


        return outputlines;


    }


    @Override
    public List<String> gymOwnerAttacks(Pokemon gymPokemon, Pokemon playPokemon) {
        FirePokemon fire;
        ElectricPokemon electric;
        GrassPokemon grass;
        WaterPokemon water;

        List<String> outputlines = new ArrayList<>();
        Textlines textlinesToReturn;
        int hp = playPokemon.getHp();


        switch (gymPokemon.getType()) {
            case "fire": {
                fire = new FirePokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());

                List<String> attacks = fire.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                outputlines = performAttackPlayer(gymPokemon, playPokemon, attack);

                break;

            }
            case "water": {
                water = new WaterPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                List<String> attacks = water.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                outputlines = performAttackPlayer(gymPokemon, playPokemon, attack);

                break;
            }

            case "grass": {
                grass = new GrassPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                List<String> attacks = grass.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                outputlines = performAttackPlayer(gymPokemon, playPokemon, attack);

                break;

            }
            default: {
                electric = new ElectricPokemon(gymPokemon.getName(), gymPokemon.getLevel(), gymPokemon.getHp(), gymPokemon.getFood(), gymPokemon.getSound());
                List<String> attacks = electric.getAttacks();
                String attack = attacks.get(randomAttackByGymOwner());
                playPokemon.setHp(hp);
                outputlines = performAttackPlayer(gymPokemon, playPokemon, attack);

                break;




            }

        }
        return outputlines;
    }

    @Override
    public void attackOrChange(String choice, Pokemon pokemon, Pokemon gymPokemon, PokemonTrainer trainer, PokemonGymOwner gym) {

    }

}


