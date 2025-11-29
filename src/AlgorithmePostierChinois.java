import java.util.*;

public class AlgorithmePostierChinois {

    public Tournee calculerTourneeEulerienne(GrapheRoutier graphe, Depot depot, Camion camion) {
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
}
