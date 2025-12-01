package ramassageHabitations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrapheRoutier {
    private String type;
    private List<Intersection> intersections = new ArrayList<>();
    private List<Route> routes_sortantes = new ArrayList<>();


    public void ajouterIntersection(Intersection i) {
        if (!intersections.contains(i)) {
            intersections.add(i);
        }
    }

    public void ajouterRoute(Route r) {
        if (!routes_sortantes.contains(r)) {
            routes_sortantes.add(r);
            ajouterIntersection(r.getOrigine());
            ajouterIntersection(r.getDestination());
        }
    }

    public List<Intersection> getIntersections() {
        return Collections.unmodifiableList(intersections);
    }

    public List<Route> getRoutes() {
        return Collections.unmodifiableList(routes_sortantes);
    }

    public String getType() { return type;}

    public List<Intersection> getVoisins(Intersection i) {
        List<Intersection> voisins = new ArrayList<>();
        for (Route r : routes_sortantes) {
            if (r.getOrigine().equals(i)) {
                voisins.add(r.getDestination());
            }
        }
        return voisins;
    }

    public Route getRoute(Intersection i1, Intersection i2) {
        for (Route r : routes_sortantes) {
            if (r.getOrigine().equals(i1) && r.getDestination().equals(i2)) {
                return r;
            }
        }
        return null;
    }
}
