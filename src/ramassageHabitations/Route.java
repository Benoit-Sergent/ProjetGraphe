package ramassageHabitations;

public class Route {
    private String nom;
    private int distance;
    private int nombre_maisons;
    private Intersection origine;
    private Intersection destination;

    public Route(int distance, Intersection origine, Intersection destination) {
        this.distance = distance;
        this.origine = origine;
        this.destination = destination;
    }

    //Getters
    public double getDistance() {
        return distance;
    }
    public Intersection getOrigine() {
        return origine;
    }
    public Intersection getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Route{" +
                ", distance=" + distance +
                ", origine=" + origine +
                ", destination=" + destination +
                '}';
    }
}
