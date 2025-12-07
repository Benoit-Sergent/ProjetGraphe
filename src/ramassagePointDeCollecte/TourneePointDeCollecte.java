package ramassagePointDeCollecte;

import Commun.Tournee;
import ramassageHabitations.DemandeEncombrant;
import ramassageHabitations.ItineraireRamassage;

import java.util.LinkedList;

public class TourneePointDeCollecte extends Tournee {
    private int capaciteMax;
    private ItinerairePointDeCollecte itineraire;

    //Getters
    public ItinerairePointDeCollecte getItineraire() { return itineraire; }

    //Constructeur
    public TourneePointDeCollecte(String AlgorithmeUtilise, ItinerairePointDeCollecte itineraire) {
        super(AlgorithmeUtilise);
        this.capaciteMax = getCamion().getCapaciteMax();
        this.itineraire = itineraire;
    }
    @Override
    public String toString() {
        return "Commun.Tournee{" +
                " capaciteMax=" + capaciteMax +
                ", camion=" + getCamion() +
                '}';
    }
}
