package CreneauxSecteurs;

import Commun.Graphe;

public class GrapheSecteurs extends Graphe {
    String type;

    //Constructeur
    public GrapheSecteurs(String categorie, String type) {
        super(categorie);
        this.type = type;
    }
}
