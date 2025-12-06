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
    public double getDistance(PointDeCollecte a, PointDeCollecte b) {
        if (matriceDistances == null || CentreDeTraitement == null) {
            return Double.POSITIVE_INFINITY;
        }

        int i = indexOf(a);
        int j = indexOf(b);
        if (i < 0 || j < 0) {
            return Double.POSITIVE_INFINITY;
        }
        return matriceDistances[i][j];
    }

    private int indexOf(PointDeCollecte p) {
        if (p == null) return -1;
        if (p == CentreDeTraitement) return 0;
        if (PDC == null) return -1;

        int idx = PDC.indexOf(p); // même instance => OK
        if (idx < 0) return -1;
        return idx + 1; // car 0 = dépôt
    }
}

