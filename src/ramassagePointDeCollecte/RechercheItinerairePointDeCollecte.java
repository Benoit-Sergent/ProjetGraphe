package ramassagePointDeCollecte;

import ramassageHabitations.GrapheRoutier;
import ramassageHabitations.Intersection;
import ramassageHabitations.Route;
import ramassageHabitations.TourneeRamassage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RechercheItinerairePointDeCollecte {

    static public TourneePointDeCollecte PlusProcheVoisinTSP(GraphePointDeCollecte graphe) {
        if (graphe == null) {
            System.out.println("Données insuffisantes pour calculer une tournée (graphe/depot/points).");
            return new TourneePointDeCollecte();
        }

        List<PointDeCollecte> nonVisites = new ArrayList<>(graphe.getPointDeCollecte());
        PointDeCollecte positionCourante = graphe.getCentreDeTraitement();

        TourneePointDeCollecte tournee = new TourneePointDeCollecte();

        /**
        double distanceTotale = 0.0;
        double chargeTotale = 0.0;

        while (!nonVisites.isEmpty()) {
            PointCollecte prochain = null;
            double meilleureDistance = Double.POSITIVE_INFINITY;

            for (PointCollecte pc : nonVisites) {
                Route route = graphe.getRoute(positionCourante, pc.getPosition());
                if (route == null) {
                    continue;
                }

                double d = route.getDistance();
                if (d < meilleureDistance) {
                    meilleureDistance = d;
                    prochain = pc;
                }
            }

            if (prochain == null) {
                System.out.println("Impossible de rejoindre certains points restants. Arrêt de l'algorithme.");
                break;
            }

            tournee.ajouterPoint(prochain);
            distanceTotale += meilleureDistance;
            chargeTotale += prochain.getQuantiteEstimee();
            positionCourante = prochain.getPosition();
            nonVisites.remove(prochain);
        }

        if (!positionCourante.equals(depot.getPosition())) {
            Route retour = graphe.getRoute(positionCourante, depot.getPosition());
            if (retour != null) {
                distanceTotale += retour.getDistance();
            } else {
                System.out.println("Attention : aucune route pour revenir au dépôt depuis la dernière position.");
            }
        }

        tournee.setDistanceTotale(distanceTotale);
        tournee.setChargeTotale(chargeTotale);

        System.out.println("Tournée calculée (Plus Proche Voisin) :");
        System.out.println("  Distance totale = " + distanceTotale);
        System.out.println("  Charge totale   = " + chargeTotale);
        System.out.println("  Points visités  = " + tournee.getPointsVisites());

        **/
        return tournee;
    }
    static public TourneePointDeCollecte MSTTSP(GraphePointDeCollecte graphe) {
        //Implémentation de l'approche MST + parcours préfixe + shortcutting
        System.out.println("Calcul de tournée avec l'approche MST (TSP)...");
        return new TourneePointDeCollecte();
    }
}
