package ramassagePointDeCollecte;

import Commun.Tournee;

public class TourneePointDeCollecte extends Tournee {
    private int capaciteMax;
    private ItinerairePointDeCollecte itineraire;

    //Getters
    public ItinerairePointDeCollecte getItineraire() { return itineraire; }

    @Override
    public String toString() {
        return "Commun.Tournee{" +
                " capaciteMax=" + capaciteMax +
                ", camion=" + getCamion() +
                '}';
    }
}
