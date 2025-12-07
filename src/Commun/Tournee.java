package Commun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Tournee {
    private String Date;
    private String Horaire;
    private String AlgorithmeUtilise;
    private Camion camion;

    //Constructeur
    public Tournee(String AlgorithmeUtilise) {
        this.AlgorithmeUtilise = AlgorithmeUtilise;
    }

    //Getters
    public String getDate() { return Date;}
    public String getHoraire() { return Horaire;}
    public String getAlgorithmeUtilise() { return AlgorithmeUtilise;}
    public Camion getCamion() {
        return camion;
    }

    //Setters
    public void setCamion(Camion camion){ this.camion = camion;}
    public void setAlgorithmeUtilise(String AlgorithmeUtilise){ this.AlgorithmeUtilise = AlgorithmeUtilise;}

    @Override
    public String toString() {
        return "Tournee - Date: " + Date + " Horaire: " + Horaire + " Algorithme utilisé: " + AlgorithmeUtilise;
    }
}

