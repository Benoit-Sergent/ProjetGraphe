package CreneauxSecteurs;
import Commun.Tournee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Secteur {
    private String nom;
    private int quantiteEstimee;
    private String jour;
    private Tournee tournee;
    private List<Secteur> secteursVoisins = new ArrayList<>();

    //Constructeur
    public Secteur(String nom, int quantiteEstimee) {
        this.nom = nom;
        this.quantiteEstimee = quantiteEstimee;
    }
    //Getters
    public String getNom() {
        return nom;
    }
    public double getQuantiteEstimee() {
        return quantiteEstimee;
    }
    public String getJour() {
        return jour;
    }
    public List<Secteur> getSecteursVoisins() { return secteursVoisins; }

    //Setters
    public void setJour(String Jour) { this.jour = Jour;}

    public void ajouterVoisin(Secteur secteur) {
        if (!secteursVoisins.contains(secteur)) {
            secteursVoisins.add(secteur);
        }
    }
}
