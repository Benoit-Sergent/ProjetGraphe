package ramassageHabitations;

import Commun.Graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheRoutier extends Graphe {
    private String type;
    private Intersection CentreDeTraitement;
    private List<Intersection> intersections = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();


    //Getters
    public List<Intersection> getIntersections() { return intersections; }
    public String getType() { return type;}
    public List<Route> getRoute() { return routes;}
}
