package CreneauxSecteurs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Secteur {
    private String nom;
    private double quantiteEstimee;
    private int couleur;
    private List<Secteur> secteursVoisins = new ArrayList<>();

    public Secteur(String nom, double quantiteEstimee, int couleur) {
        this.nom = nom;
        this.quantiteEstimee = quantiteEstimee;
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public double getQuantiteEstimee() {
        return quantiteEstimee;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public void ajouterVoisin(Secteur secteur) {
        if (!secteursVoisins.contains(secteur)) {
            secteursVoisins.add(secteur);
        }
    }

    public List<Secteur> getSecteursVoisins() {
        return Collections.unmodifiableList(secteursVoisins);
    }
}
