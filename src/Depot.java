public class Depot {
    private String nom;
    private Intersection position;

    public Depot(String nom, Intersection position) {
        this.nom = nom;
        this.position = position;
    }

    public String getNom() {
        return nom;
    }

    public Intersection getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "nom='" + nom + '\'' +
                ", position=" + position +
                '}';
    }
}
