package ramassagePointDeCollecte;

public class PointDeCollecte {
    private int quantiteDechets;
    private String nom;

    //Getters
    public double getQuantiteDechets() {
        return quantiteDechets;
    }


    @Override
    public String toString() {
        return "PointCollecte{" +
                ", quantiteEstimee=" + quantiteDechets +
                '}';
    }
}
