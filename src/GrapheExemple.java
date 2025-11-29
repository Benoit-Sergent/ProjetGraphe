import java.util.ArrayList;
import java.util.List;

public class GrapheExemple {

    public static Depot creerDepot(GrapheRoutier graphe) {
        if (graphe.getIntersections().isEmpty()) {
            throw new IllegalStateException("Aucune intersection dans le graphe.");
        }
        Intersection depotIntersection = graphe.getIntersections().get(0);
        return new Depot("Depot", depotIntersection);
    }

    public static Camion creerCamion(Depot depot) {
        return new Camion(1, 10.0, depot);
    }

    public static List<PointCollecte> creerPointsCollecte(GrapheRoutier graphe) {
        List<PointCollecte> points = new ArrayList<>();
        List<Intersection> intersections = graphe.getIntersections();

        int idPoint = 1;
        for (int i = 1; i < intersections.size(); i++) {
            Intersection inter = intersections.get(i);

            TypePoint type;
            switch (i % 3) {
                case 1:
                    type = TypePoint.POUBELLE;
                    break;
                case 2:
                    type = TypePoint.ENCOMBRANT;
                    break;
                default:
                    type = TypePoint.POINT_COLLECTE;
                    break;
            }

            double quantiteEstimee = 2.0;
            PointCollecte p = new PointCollecte(idPoint, type, quantiteEstimee, inter);
            points.add(p);
            idPoint++;
        }

        return points;
    }
}
