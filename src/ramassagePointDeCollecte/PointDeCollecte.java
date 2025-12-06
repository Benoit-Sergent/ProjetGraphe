package ramassagePointDeCollecte;

public class PointDeCollecte {
    private int quantiteDechets;
    private String nom;

    // Constructeurs
    public PointDeCollecte(String nom) {
        this(nom, 0);
    }

    public PointDeCollecte(String nom, int quantiteDechets) {
        this.nom = nom;
        this.quantiteDechets = quantiteDechets;
    }

    //Getters
    public double getQuantiteDechets() {
        return quantiteDechets;
    }

    public String getNom() {
        return nom;
    }

    // Setters (serviront pour HO2/HO3 plus tard)
    public void setQuantiteDechets(int quantiteDechets) {
        this.quantiteDechets = quantiteDechets;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom + " (q=" + quantiteDechets + ")";
    }
}
