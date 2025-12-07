package ramassageHabitations.Graphe;

public class Route {
    private String nom;
    private int distance;
    private int nombre_maisons;
    private Intersection depart;
    private Intersection arrivee;

    public Route(String nom, int distance, Intersection depart, Intersection arrivee) {
        this.nom = nom;
        this.distance = distance;
        this.depart = depart;
        this.arrivee = arrivee;
    }

    //Getters
    public int getDistance() {
        return distance;
    }
    public Intersection getDepart() {
        return depart;
    }
    public Intersection getArrivee() {
        return arrivee;
    }
    public String getNom() { return nom;}
    public int getNombre_maisons() { return nombre_maisons;}

    @Override
    public String toString() {
        return "Route{" +
                ", distance=" + distance +
                ", origine=" + depart +
                ", destination=" + arrivee +
                '}';
    }
}
