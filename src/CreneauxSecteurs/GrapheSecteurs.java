package CreneauxSecteurs;

import Commun.Graphe;

import java.util.*;

public class GrapheSecteurs extends Graphe {
    private LinkedList<Secteur> secteurs = new LinkedList<>();

    //Getters
    public List<Secteur> getSecteurs() { return secteurs; }
}