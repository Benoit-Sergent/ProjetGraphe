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
    public List<Secteur> getSecteursVoisins() { return Collections.unmodifiableList(secteursVoisins);}

    //Setters
    public void setCouleur(String Jour) { this.jour = Jour;}

    public void ajouterVoisin(Secteur secteur) {
        if (!secteursVoisins.contains(secteur)) {
            secteursVoisins.add(secteur);
        }
    }
}
