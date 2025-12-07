package ramassageHabitations.Graphe;

import Commun.Graphe;
import ramassageHabitations.DemandeEncombrant;

import java.util.*;

public class GrapheRoutier extends Graphe {
    private String type;
    private Intersection CentreDeTraitement;
    private List<Intersection> intersections = new LinkedList<>();
    private Map<String,Route> routes = new HashMap<>();

    //Getters
    public List<Intersection> getIntersections() { return intersections; }
    public Intersection getCentreDeTraitement() { return CentreDeTraitement; }
    public String getType() { return type;}
    public Map<String,Route> getRoute() { return routes;}

    //Setters
    public void setCentreDeTraitement(Intersection centreDeTraitement) {
        CentreDeTraitement = centreDeTraitement;
    }
    public void setType(String type) { this.type = type; }
}
