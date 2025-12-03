package ramassagePointDeCollecte;

import Commun.Graphe;

import java.util.LinkedList;

public class GraphePointDeCollecte extends Graphe {
    private String type;
    private PointDeCollecte CentreDeTraitement;
    private LinkedList<PointDeCollecte> PDC;

    //Getters
    public String getType() { return type; }
    public PointDeCollecte getCentreDeTraitement() { return CentreDeTraitement; }
    public LinkedList<PointDeCollecte> getPointDeCollecte() { return PDC; }
}
