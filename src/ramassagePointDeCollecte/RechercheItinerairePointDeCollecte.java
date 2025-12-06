package ramassagePointDeCollecte;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RechercheItinerairePointDeCollecte {

    public static TourneePointDeCollecte PlusProcheVoisinTSP(GraphePointDeCollecte graphe) {

        // Blindage
        if (graphe == null
                || graphe.getCentreDeTraitement() == null
                || graphe.getPointDeCollecte() == null
                || graphe.getMatriceDistances() == null) {

            System.out.println("Erreur : graphe PDC incomplet");
            return new TourneePointDeCollecte("TSP", null);
        }

        PointDeCollecte depot = graphe.getCentreDeTraitement();
        // Copie de la liste des PDC : ce sont les points non encore visités
        List<PointDeCollecte> nonVisites = new ArrayList<>(graphe.getPointDeCollecte());
        LinkedList<PointDeCollecte> ordreVisite = new LinkedList<>();
        PointDeCollecte sommetActuel = depot;
        ordreVisite.add(depot);

        int distanceTotale = 0;

        // Tant qu'il reste des points à visiter
        while (!nonVisites.isEmpty()) {
            PointDeCollecte sommetPlusProche = null;
            double distanceMinimale = Double.POSITIVE_INFINITY;

            // Chercher parmi les non visités celui qui est le plus proche du sommet actuel
            for (PointDeCollecte candidat : nonVisites) {
                double d = graphe.getDistance(sommetActuel, candidat);
                if (d < distanceMinimale) {
                    distanceMinimale = d;
                    sommetPlusProche = candidat;
                }
            }
            if (sommetPlusProche == null || distanceMinimale == Double.POSITIVE_INFINITY) {
                System.out.println("Impossible de trouver un prochain point de collecte atteignable.");
                break;
            }
            // Ajouter la distance jusqu'au plus proche voisin
            distanceTotale += (int) Math.round(distanceMinimale);

            // Ajouter ce sommet dans l'itinéraire
            ordreVisite.add(sommetPlusProche);

            // Mettre à jour la position actuelle et enlever ce point des non visités
            sommetActuel = sommetPlusProche;
            nonVisites.remove(sommetPlusProche);
        }
        // Retour au dépôt
        if (sommetActuel != depot) {
            double dRetour = graphe.getDistance(sommetActuel, depot);
            if (dRetour != Double.POSITIVE_INFINITY) {
                distanceTotale += (int) Math.round(dRetour);
                ordreVisite.add(depot);
            } else {
                System.out.println("Attention : aucune distance pour revenir au dépôt.");
            }
        }
        // Construire l'itinéraire
        // HO1 : on ignore la capacité pour l'instant
        ItinerairePointDeCollecte itineraire = new ItinerairePointDeCollecte(distanceTotale, 0, ordreVisite);
        // Construire la tournée
        TourneePointDeCollecte tournee = new TourneePointDeCollecte("TSP",itineraire);
        return tournee;
    }


    static public TourneePointDeCollecte MSTTSP(GraphePointDeCollecte graphe) {
        //Implémentation de l'approche MST + parcours préfixe + shortcutting
        System.out.println("Calcul de tournée avec l'approche MST (TSP)...");
        return new TourneePointDeCollecte(null, null);
    }
}
