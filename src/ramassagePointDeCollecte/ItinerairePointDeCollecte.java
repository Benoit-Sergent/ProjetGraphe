package ramassagePointDeCollecte;

import Commun.Itineraire;
import ramassagePointDeCollecte.Graphe.PointDeCollecte;

import java.util.LinkedList;

public class ItinerairePointDeCollecte extends Itineraire {
    // Liste des points visités dans l’ordre
    private LinkedList<PointDeCollecte> itineraire;

    //Constructeur
    public ItinerairePointDeCollecte(int distance, int quantite_dechet, LinkedList<PointDeCollecte> ordreVisite) {
        super(distance, quantite_dechet);
        this.itineraire = ordreVisite;
    }
    // Getters
    public LinkedList<PointDeCollecte> getItineraire() {
        return itineraire;
    }
    // Setters
    public void setItineraire(LinkedList<PointDeCollecte> itineraire) {
        this.itineraire = itineraire;
    }

    @Override
    public String toString() {
        if (itineraire == null || itineraire.isEmpty()) {
            return "Itinéraire vide";
        }

        StringBuilder sb = new StringBuilder();

        // Nombre de points distincts visités (hors dépôt de fin)
        int nbPointsVisites = itineraire.size();
        if (itineraire.getFirst().equals(itineraire.getLast())) {
            nbPointsVisites = nbPointsVisites - 1; // on ne compte pas deux fois le dépôt
        }

        sb.append("Nombre de points visités : ").append(nbPointsVisites).append("\n");

        sb.append("Chemin : ");
        for (int i = 0; i < itineraire.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(itineraire.get(i).getNom());
        }

        sb.append("\nDistance totale : ").append(getDistance()).append(" m");

        return sb.toString();
    }
}

