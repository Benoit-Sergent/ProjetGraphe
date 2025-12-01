package ramassageHabitations;

import java.util.*;

public class RechercheItineraireRamassage {
    static public TourneeRamassage postierChinois(GrapheRoutier graphe, Depot depot, Camion camion) {
        Tournee tournee = new Tournee(camion, depot);
        if (graphe == null || depot == null || camion == null) {
            return tournee;
        }

        Map<Intersection, List<Route>> adj = new HashMap<>();
        for (Intersection i : graphe.getIntersections()) {
            adj.put(i, new ArrayList<>());
        }
        for (Route r : graphe.getRoutes()) {
            adj.computeIfAbsent(r.getOrigine(), k -> new ArrayList<>()).add(r);
            if (!graphe.isOriente()) {
                adj.computeIfAbsent(r.getDestination(), k -> new ArrayList<>()).add(r);
            }
        }

        Intersection start = depot.getPosition();
        if (!adj.containsKey(start)) {
            return tournee;
        }

        Deque<Intersection> stack = new ArrayDeque<>();
        List<Intersection> circuit = new ArrayList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Intersection v = stack.peek();
            List<Route> edges = adj.get(v);
            while (edges != null && !edges.isEmpty() && edges.get(0) == null) {
                edges.remove(0);
            }
            if (edges == null || edges.isEmpty()) {
                circuit.add(stack.pop());
            } else {
                Route r = edges.remove(0);
                Intersection u;
                if (r.getOrigine().equals(v)) {
                    u = r.getDestination();
                } else {
                    u = r.getOrigine();
                }
                List<Route> edgesU = adj.get(u);
                if (edgesU != null) {
                    edgesU.remove(r);
                }
                stack.push(u);
            }
        }

        double distanceTotale = 0.0;
        for (int i = 0; i < circuit.size() - 1; i++) {
            Intersection a = circuit.get(i);
            Intersection b = circuit.get(i + 1);
            if (a.equals(b)) continue;
            Route r = graphe.getRoute(a, b);
            if (r != null) {
                distanceTotale += r.getDistance();
            }
        }

        tournee.setDistanceTotale(distanceTotale);
        tournee.setChargeTotale(0.0);
        return tournee;
    }
    static public TourneeRamassage PlusProcheVoisinTSP(GrapheRoutier graphe, Depot depot, List<PointCollecte> points) {

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
    static public TourneeRamassage MSTTSP(GrapheRoutier graphe, Depot depot, List<PointCollecte> points) {
        //Implémentation de l'approche MST + parcours préfixe + shortcutting
        System.out.println("Calcul de tournée avec l'approche MST (TSP)...");
        return new TourneeRamassage(null, depot);
    }
}
