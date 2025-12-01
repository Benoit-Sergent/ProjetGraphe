package CreneauxSecteurs;

import Commun.Graphe;

import java.util.LinkedList;

public class GrapheSecteurs extends Graphe {
    private LinkedList<Secteur> secteurs = new LinkedList<>();

    //Getters
    public LinkedList<Secteur> getSecteurs() { return secteurs; }
}
