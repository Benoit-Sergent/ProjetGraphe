import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrapheRoutier {
    private List<Intersection> intersections = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private boolean oriente;

    public GrapheRoutier(boolean oriente) {
        this.oriente = oriente;
    }

    public void ajouterIntersection(Intersection i) {
        if (!intersections.contains(i)) {
            intersections.add(i);
        }
    }

    public void ajouterRoute(Route r) {
        if (!routes.contains(r)) {
            routes.add(r);
            ajouterIntersection(r.getOrigine());
            ajouterIntersection(r.getDestination());
        }
    }

    public List<Intersection> getIntersections() {
        return Collections.unmodifiableList(intersections);
    }

    public List<Route> getRoutes() {
        return Collections.unmodifiableList(routes);
    }

    public boolean isOriente() {
        return oriente;
    }

    public List<Intersection> getVoisins(Intersection i) {
        List<Intersection> voisins = new ArrayList<>();
        for (Route r : routes) {
            if (r.getOrigine().equals(i)) {
                voisins.add(r.getDestination());
            }
            if (!oriente && r.getDestination().equals(i)) {
                voisins.add(r.getOrigine());
            }
        }
        return voisins;
    }

    public Route getRoute(Intersection i1, Intersection i2) {
        for (Route r : routes) {
            if (r.getOrigine().equals(i1) && r.getDestination().equals(i2)) {
                return r;
            }
            if (!oriente && r.getOrigine().equals(i2) && r.getDestination().equals(i1)) {
                return r;
            }
        }
        return null;
    }
}
