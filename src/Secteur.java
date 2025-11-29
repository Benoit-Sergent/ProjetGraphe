import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Secteur {
    private int id;
    private String nom;
    private double quantiteEstimee;
    private int couleur;
    private List<PointCollecte> points = new ArrayList<>();
    private List<Secteur> secteursVoisins = new ArrayList<>();

    public Secteur(int id, String nom, double quantiteEstimee, int couleur) {
        this.id = id;
        this.nom = nom;
        this.quantiteEstimee = quantiteEstimee;
        this.couleur = couleur;
    }

    public int getId() {
        return id;
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

    public void ajouterPoint(PointCollecte point) {
        if (!points.contains(point)) {
            points.add(point);
        }
    }

    public void ajouterVoisin(Secteur secteur) {
        if (!secteursVoisins.contains(secteur)) {
            secteursVoisins.add(secteur);
        }
    }

    public List<PointCollecte> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public List<Secteur> getSecteursVoisins() {
        return Collections.unmodifiableList(secteursVoisins);
    }
}
