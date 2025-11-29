import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra implements AlgorithmePlusCourtChemin {

    @Override
    public List<Intersection> plusCourtChemin(GrapheRoutier g, Intersection src, Intersection dest) {
        List<Intersection> result = new ArrayList<>();
        if (g == null || src == null || dest == null) {
            return result;
        }

        Map<Intersection, Double> dist = new HashMap<>();
        Map<Intersection, Intersection> previous = new HashMap<>();
        Set<Intersection> visited = new HashSet<>();

        for (Intersection i : g.getIntersections()) {
            dist.put(i, Double.POSITIVE_INFINITY);
            previous.put(i, null);
        }
        if (!dist.containsKey(src)) {
            return result;
        }
        dist.put(src, 0.0);

        while (true) {
            Intersection u = null;
            double best = Double.POSITIVE_INFINITY;
            for (Map.Entry<Intersection, Double> e : dist.entrySet()) {
                Intersection node = e.getKey();
                double d = e.getValue();
                if (!visited.contains(node) && d < best) {
                    best = d;
                    u = node;
                }
            }

            if (u == null) {
                break;
            }

            if (u.equals(dest)) {
                break;
            }

            visited.add(u);

            for (Intersection v : g.getVoisins(u)) {
                if (visited.contains(v)) {
                    continue;
                }
                Route r = g.getRoute(u, v);
                if (r == null) {
                    continue;
                }
                double alt = dist.get(u) + r.getDistance();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    previous.put(v, u);
                }
            }
        }

        if (!dist.containsKey(dest) || dist.get(dest) == Double.POSITIVE_INFINITY) {
            return result;
        }

        Intersection step = dest;
        while (step != null) {
            result.add(0, step);
            step = previous.get(step);
        }

        return result;
    }
}
