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

    @Override
    public String toString() {
        if (ordreVisite == null || ordreVisite.isEmpty()) {
            return "Itinéraire vide";
        }

        StringBuilder sb = new StringBuilder();

        // Nombre de points distincts visités (hors dépôt de fin)
        int nbPointsVisites = ordreVisite.size();
        if (ordreVisite.getFirst().equals(ordreVisite.getLast())) {
            nbPointsVisites = nbPointsVisites - 1; // on ne compte pas deux fois le dépôt
        }

        sb.append("Nombre de points visités : ").append(nbPointsVisites).append("\n");

        sb.append("Chemin : ");
        for (int i = 0; i < ordreVisite.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(ordreVisite.get(i).getNom());
        }

        sb.append("\nDistance totale : ").append(getDistance()).append(" m");

        return sb.toString();
    }
}

