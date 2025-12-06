package ramassagePointDeCollecte;

import Commun.Tournee;
import ramassageHabitations.DemandeEncombrant;
import ramassageHabitations.ItineraireRamassage;

import java.util.LinkedList;

public class TourneePointDeCollecte extends Tournee {
    private int capaciteMax;
    private ItinerairePointDeCollecte itineraire;

    //Constructeur
    public TourneePointDeCollecte(String AlgorithmeUtilise, ItinerairePointDeCollecte itineraire) {
        super(AlgorithmeUtilise);
        this.itineraire = itineraire;
    }
    // Getters
    public ItinerairePointDeCollecte getItineraire() {
        return itineraire;
    }
    public int getCapaciteMax() {
        return capaciteMax;
    }

    // Setters
    public void setItineraire(ItinerairePointDeCollecte itineraire) {
        this.itineraire = itineraire;
    }
    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }


    @Override
    public String toString() {
        return "TourneePointDeCollecte{" +
                "capaciteMax=" + capaciteMax +
                ", camion=" + getCamion() +
                '}';
    }
}
