## Inleiding

Deze applicatie is een toepassing van scope, overloading, keywords en interfaces mbv Pokémons!

## Applicatiebeschrijving

Een speler kan via frontend-react applicatie een spel kan spelen. Degenen die bekend zijn met Pokémon weten
dat deze altijd een _type_ hebben (sterker nog: Pokémon kunnen meerdere types tegelijk zijn, maar om het niet te moeilijk
te maken, maken we gebruik van één type per Pokémon). Voorbeelden van deze typen zijn: Fire 🔥, Water 🌊, Grass 🌿 en
Electric ⚡. Daarnaast gaan we een gym maken en een trainer, zodat de applicatie interactief kan worden.

Naast hun type, hebben Pokémon natuurlijk bepaalde eigenschappen met elkaar gemeen. Daarom maken we gebruik van een
**super** klasse. Ook gaan we kijken naar overloading, polymorfisme en een interface.


![pokemon.JPG](assets%2Fpokemon.JPG)


### De aanval methodes

De aanval methodes hebben de volgende eigenschappen:
- een `textline toevoegen` die vertelt wie wie aanvalt met welke aanval (concatenation met variabelen, b.v.: "Bulbasaur attacks Charizard with leafStorm");
- een beslissingsstructuur die kijkt welk type de vijand heeft;
- aan de hand van de beslissingsstructuur moet de vijand hp punten verliezen (per type verschilt het aantal punten);
- een `textline toevoegen` die aangeeft wat de aanval voor effect heeft (concatenation met variabelen, b.v.: "Charizard loses 15 hp");
- de hp van de vijand verlagen;
- tenslotte een `textline toevoegen` die de resteren hp waarde weergeeft van de vijand;

De volgende methodes hebben nog een extra functie:
- `rainDance` heeft geen effect op electric Pokemons maar gaat een `textline toevoegen` met de boodschap: "has no effect on (vijand)";
- `rainDance` levert een hp boost aan vijanden grass-types;
- `thunder` levert een hp boost aan electric Pokemons;
- `leechSeed` trekt hp van de vijand af en geeft deze hp aan de aanvallende Pokémon terug;


### Spel logica

Bij Pokémons is het zo dat het type bepalend is voor hoe zwaar de schade is. Hieronder vind je een overzichtje voor een logica van schade bij aanvallen:
- Het type fire🔥 doet de meeste schade aan grass-pokémons🌿, daarna aan water-pokémons🌊, dan de electric-pokémons⚡ en het minste bij fire-pokémons🔥.
- Het type grass🌿 doet de meeste schade aan electric-pokémons⚡, daarna aan fire-pokémons🔥, dan de water-pokémons🌊 en het minste bij grass-pokémons🌿.
- Het type electric⚡ doet de meeste schade aan water-pokémons🌊, daarna aan grass-pokémons🌿, dan de fire-pokémons 🔥en het minste bij electric-pokémons⚡.
- Het type water🌊 doet de meeste schade aan fire-pokémons🔥, daarna aan electric-pokémons⚡, dan de grass-pokémons🌿 en het minste bij water-pokémons🌊.

## Randvoorwaarden

De applicatie bevat hetvolgende:

- Een `abstract` super klasse  met 5 `private` `final`  variabelen,  1 `private`variabele, 1 constructor, 6 getters en 1 setter;
- 4 subklassen die, naast alles dat ze erven, zelf ook minimaal 2 `private` `final` variabelen, 1 constructor, 4 methodes, 1 getter bevatten;
- Een `Main`-klasse met een `main`-methode;
- Een `interface` met 11 methodes;
- Een implementatie (-Impl) van de `interface` waarin alle methodes worden overschreven met een `@Override`;
- 2 "normale" klassen. Waarvan een klasse met 8 variabelen, constructor, getters en setters. De andere klasse extends de eerste klasse en heeft zelf nog 1 variabele, een getter, setter en een constructor die enkel de super aanroept. 


Op dit moment kun je het spel oneindig opnieuw spelen. 
Als je doorspeelt en er is een Pokémon die geen hp meer heeft, dan kan die niet meer gebruikt worden, tenzij het spel opnieuw wordt opgestart.

