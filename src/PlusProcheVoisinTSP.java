import java.util.ArrayList;
import java.util.List;

public class PlusProcheVoisinTSP implements AlgorithmeItineraire {

    @Override
    public Tournee calculerTournee(GrapheRoutier graphe, Depot depot, List<PointCollecte> points) {

        if (graphe == null || depot == null || points == null || points.isEmpty()) {
            System.out.println("Données insuffisantes pour calculer une tournée (graphe/depot/points).");
            return new Tournee(null, depot);
        }

        List<PointCollecte> nonVisites = new ArrayList<>(points);

        Intersection positionCourante = depot.getPosition();

        Tournee tournee = new Tournee(null, depot);

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

        return tournee;
    }
}
