package ramassagePointDeCollecte;

import Commun.Itineraire;
import java.util.LinkedList;

public class ItinerairePointDeCollecte extends Itineraire {

    // Liste des points visités dans l’ordre
    private LinkedList<PointDeCollecte> ordreVisite;

    // Chemins détaillés
    private LinkedList<Chemin> itineraire;

    // Getters
    public LinkedList<PointDeCollecte> getOrdreVisite() {
        return ordreVisite;
    }
    public LinkedList<Chemin> getItineraire() {
        return itineraire;
    }

    // Setters
    public void setOrdreVisite(LinkedList<PointDeCollecte> ordreVisite) {
        this.ordreVisite = ordreVisite;
    }
    public void setItineraire(LinkedList<Chemin> itineraire) {
        this.itineraire = itineraire;
    }
}

