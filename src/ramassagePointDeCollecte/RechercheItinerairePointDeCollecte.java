package ramassagePointDeCollecte;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RechercheItinerairePointDeCollecte {

    // Algorithme TSP
    // Méthode qui retourne TourneePointDeCollecte
    public static TourneePointDeCollecte PlusProcheVoisinTSP(GraphePointDeCollecte graphe) {

        // Blindage
        if (graphe == null
                || graphe.getCentreDeTraitement() == null // graphe non null
                || graphe.getPointDeCollecte() == null // dépôt non défini
                || graphe.getMatriceDistances() == null) { // matrice non vide

            System.out.println("Erreur : Graphe PDC incomplet.");
            return new TourneePointDeCollecte();
        }

        // On récupère le centre de traitement
        PointDeCollecte depot = graphe.getCentreDeTraitement();
        // On copie la liste des PDC pour créer la liste des sommets non visités
        List<PointDeCollecte> nonVisites = new ArrayList<>(graphe.getPointDeCollecte());

        // Liste des PDC dans l'ordre où ils sont visités
        LinkedList<PointDeCollecte> ordreVisite = new LinkedList<>();
        // Sommet où se trouve le camion à l'instant t (il commence au dépôt)
        PointDeCollecte sommetActuel = depot;
        ordreVisite.add(depot);

        int distanceTotale = 0;

        // Tant qu'il reste des points à visiter
        while (!nonVisites.isEmpty()) {
            PointDeCollecte sommetPlusProche = null;
            double distanceMinimale = Double.POSITIVE_INFINITY; // on initialise à l'infini pour toujours trouver un minimum

            // On parcourt les points non visités un par un
            for (PointDeCollecte candidat : nonVisites) {
                // on obtient la distance entre le sommet où se trouve le camion et le sommet sélectionné
                double d = graphe.getDistance(sommetActuel, candidat);
                if (d < distanceMinimale) {
                    distanceMinimale = d;
                    sommetPlusProche = candidat;
                }
            }

            if (sommetPlusProche == null || distanceMinimale == Double.POSITIVE_INFINITY) {
                System.out.println("Erreur : Impossible de trouver un prochain point de collecte atteignable.");
                break;
            }

            // On met à jour la distance totale
            distanceTotale += (int) Math.round(distanceMinimale);

            // On ajoute le sommet choisi dans la liste pour l'ordre de visite
            ordreVisite.add(sommetPlusProche);

            // On met à jour la position du camion et enlève le sommet des non visités
            sommetActuel = sommetPlusProche;
            nonVisites.remove(sommetPlusProche);
        }

        // Retour au dépôt
        // On ajoute un dernier trajet jusqu'au dépôt pour terminer la tournée
        if (sommetActuel != depot) {
            double dRetour = graphe.getDistance(sommetActuel, depot);
            if (dRetour != Double.POSITIVE_INFINITY) {
                distanceTotale += (int) Math.round(dRetour);
                ordreVisite.add(depot);
            } else {
                System.out.println("Erreur : Aucune distance pour revenir au dépôt.");
            }
        }

        // Obtention de l'itinéraire
        // Objet itineraire : contient l'ordre de visite, la distance totale
        ItinerairePointDeCollecte itineraire = new ItinerairePointDeCollecte();
        // On enregistre la liste d'ordre de visite et la distance totale dans l'itinéraire
        itineraire.setOrdreVisite(ordreVisite);
        itineraire.setDistance(distanceTotale);
        itineraire.setQuantite_dechet(0); // pas de capacité encore

        // Obtention de la tournée
        TourneePointDeCollecte tournee = new TourneePointDeCollecte();
        tournee.setItineraire(itineraire);

        return tournee;
    }

    static public TourneePointDeCollecte MSTTSP(GraphePointDeCollecte graphe) {
        //Implémentation de l'approche MST + parcours préfixe + shortcutting
        System.out.println("Calcul de tournée avec l'approche MST (TSP)...");
        return new TourneePointDeCollecte();
    }
}
