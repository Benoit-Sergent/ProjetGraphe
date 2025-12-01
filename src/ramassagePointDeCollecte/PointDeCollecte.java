package ramassagePointDeCollecte;

public class PointDeCollecte {
    private int id;
    private TypePoint type;
    private double quantiteEstimee;
    private Intersection position;

    public PointDeCollecte(int id, TypePoint type, double quantiteEstimee, Intersection position) {
        this.id = id;
        this.type = type;
        this.quantiteEstimee = quantiteEstimee;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public TypePoint getType() {
        return type;
    }

    public double getQuantiteEstimee() {
        return quantiteEstimee;
    }

    public Intersection getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "PointCollecte{" +
                "id=" + id +
                ", type=" + type +
                ", quantiteEstimee=" + quantiteEstimee +
                ", position=" + position +
                '}';
    }
}
