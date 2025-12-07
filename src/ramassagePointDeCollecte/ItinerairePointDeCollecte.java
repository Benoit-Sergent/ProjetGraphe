package ramassagePointDeCollecte;

import Commun.Itineraire;

import java.util.LinkedList;

public class ItinerairePointDeCollecte extends Itineraire {
    private LinkedList<Chemin> itineraire;

    //Constructeur
    public  ItinerairePointDeCollecte(int distance, int quantite_dechet, LinkedList<Chemin> itineraire) {
        super(distance, quantite_dechet);
        this.itineraire = itineraire;
    }
    //Getters
    public LinkedList<Chemin> getItineraire() { return itineraire; }
}
