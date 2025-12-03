package ramassagePointDeCollecte;

import Commun.Itineraire;

import java.util.LinkedList;

public class ItinerairePointDeCollecte extends Itineraire {
    private LinkedList<Chemin> itineraire;

    //Getters
    public LinkedList<Chemin> getItineraire() { return itineraire; }
}
