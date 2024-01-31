package nl.novi.backendjavapokemonplay.entity;

import java.util.Arrays;
import java.util.List;

public class Textlines {

    private List<String> textlines;

    public Textlines (List<String> textlines) {
       this.textlines = textlines;
    }

  //  public Long getId() {return id; }

    public List<String> getTextlines() {
        return textlines;
    }

  //  public void setId(Long id) {this.id = id;    }

    public void setTextlines(List<String> textlines) {
        this.textlines = textlines;
    }
}
