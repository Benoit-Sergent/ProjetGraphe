package ramassageHabitations.Graphe;

import java.util.LinkedList;

public class Intersection {
    private String nom;
    private LinkedList<Route> routes_sortantes =  new LinkedList<>();

    //Constructeur
    public Intersection(String nom) {
        this.nom = nom;
    }

    //Getters
    public String getNom() {
        return nom;
    }
    public LinkedList<Route> getRoutesSortantes() { return routes_sortantes; }

    @Override
    public String toString() {
        return "Intersection : " + nom;
    }
}
