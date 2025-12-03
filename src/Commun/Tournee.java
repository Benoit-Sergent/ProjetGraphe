package Commun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Tournee {
    private String Date;
    private String Horaire;
    private String AlgorithmeUtilise;
    private Camion camion;

    //Getters
    public String getDate() { return Date;}
    public String getHoraire() { return Horaire;}
    public String getAlgorithmeUtilise() { return AlgorithmeUtilise;}
    public Camion getCamion() {
        return camion;
    }
}

