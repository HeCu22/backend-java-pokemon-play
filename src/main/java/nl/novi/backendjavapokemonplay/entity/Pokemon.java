package nl.novi.backendjavapokemonplay.entity;

public abstract class Pokemon {
    private final String name;
    private int hp;

    private final int level;

    private final String food;
    private final String sound;

    private final String type;

    public Pokemon(String name, int hp, int level, String food, String sound, String type) {
        this.name = name;
        this.hp = hp;
        this.level = level;
        this.food = food;
        this.sound = sound;
        this.type = type;
    }




    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }

    public String getFood() {
        return food;
    }

    public String getSound() {
        return sound;
    }

    public String getType() {
        return type;
    };

    public void setHp(int hp) {
        this.hp = hp;
    }
}
