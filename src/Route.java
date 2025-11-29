public class Route {
    private int id;
    private double distance;
    private boolean sensUnique;
    private Intersection origine;
    private Intersection destination;

    public Route(int id, double distance, boolean sensUnique,
                 Intersection origine, Intersection destination) {
        this.id = id;
        this.distance = distance;
        this.sensUnique = sensUnique;
        this.origine = origine;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isSensUnique() {
        return sensUnique;
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
                "id=" + id +
                ", distance=" + distance +
                ", sensUnique=" + sensUnique +
                ", origine=" + origine +
                ", destination=" + destination +
                '}';
    }
}
