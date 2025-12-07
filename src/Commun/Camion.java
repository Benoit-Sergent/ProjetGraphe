package Commun;

public class Camion {
    private String immatriculation;
    private int capacite = 0;
    private int capaciteMax;

    //Constructeur
    public Camion(int capaciteMax, String immatriculation) {
        this.immatriculation = immatriculation;
        this.capaciteMax = capaciteMax;
    }

    //Getters
    public int getCapaciteMax() {
        return capaciteMax;
    }

    @Override
    public String toString() {
        return "Camion - Immatriculation: " + immatriculation + " capacité max: " + capaciteMax;
    }
}
