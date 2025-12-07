package ramassagePointDeCollecte;

import Commun.Graphe;
import java.util.LinkedList;

public class GraphePointDeCollecte extends Graphe {
    private String type; // "HO1", "HO2", etc. si tu veux
    private PointDeCollecte CentreDeTraitement;
    private LinkedList<PointDeCollecte> PDC;
    private double[][] matriceDistances; // [0 = dépôt][1..n = PDC]

    // Getters
    public String getType() { return type; }
    public PointDeCollecte getCentreDeTraitement() { return CentreDeTraitement; }
    public LinkedList<PointDeCollecte> getPointDeCollecte() { return PDC; }
    public double[][] getMatriceDistances() { return matriceDistances; }

    // Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setCentreDeTraitement(PointDeCollecte centreDeTraitement) {
        CentreDeTraitement = centreDeTraitement;
    }

    public void setPointDeCollecte(LinkedList<PointDeCollecte> PDC) {
        this.PDC = PDC;
    }

    public void setMatriceDistances(double[][] matriceDistances) {
        this.matriceDistances = matriceDistances;
    }

    /**
     * Distance entre deux points (ou dépôt) en utilisant la matrice.
     * Convention d’index :
     *   0 -> dépôt
     *   1..n -> PDC dans l’ordre de la liste PDC.
     */
    // Renvoie un double (la distance)
    // Distance entre deux objets a et b
    public double getDistance(PointDeCollecte a, PointDeCollecte b) {
        // Blindage : matrice existante, dépôt initialisé
        if (matriceDistances == null || CentreDeTraitement == null) {
            return Double.POSITIVE_INFINITY;
        }

        // On cherche à savoir quel numéro correspond à quel sommet
        int i = indexOf(a);
        int j = indexOf(b);
        if (i < 0 || j < 0) {
            return Double.POSITIVE_INFINITY;
        }
        return matriceDistances[i][j];
    }

    // Méthode qui donne l'indice exact à utiliser dans la matrice de distances
    private int indexOf(PointDeCollecte p) {
        if (p == null) return -1;
        if (p == CentreDeTraitement) return 0; // le dépôt est le premier sommet dans la matrice
        if (PDC == null) return -1;

        int idx = PDC.indexOf(p); // Dans la liste des PDC : 0 = C1, 1 = C2, 2 = C3, etc
        if (idx < 0) return -1;
        return idx + 1; // On renvoie idx+1 car les PDC dans la matrice commencent à partir de la colonne 1 (dépôt = 0)
    }
}

