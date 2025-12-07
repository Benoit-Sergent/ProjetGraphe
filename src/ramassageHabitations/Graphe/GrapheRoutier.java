package ramassageHabitations.Graphe;

import Commun.Graphe;
import Commun.ModificationGraphe;
import ramassageHabitations.Algorithmes.AlgoCouplage;
import ramassageHabitations.TourneeRamassage;

import java.util.*;

public class GrapheRoutier extends Graphe {
    private String type;
    private Intersection CentreDeTraitement;
    private List<Intersection> intersections = new LinkedList<>();
    private Map<String,Route> routes = new HashMap<>();

    //Vérifie si le graphe est eulérien
    public boolean estEulerien() {
        for (Intersection i : intersections) { //Pour chaque intersection..
            if (i.getDegre() % 2 != 0) {       //Si le degré n'est pas paire
                return false;                  //Alors c'est faux
            }
        }
        return true;
    }

    /**
     * Logique privée pour corriger un graphe non eulérien (Postier Chinois).
     */
    public boolean devenirEulerien(TourneeRamassage tr) {
        // A. Identifier les sommets impairs
        List<Intersection> impairs = new ArrayList<>();
        for (Intersection i : intersections) {
            if (i.getDegre() % 2 != 0) impairs.add(i);
        }
        System.out.println("   -> " + impairs.size() + " sommets impairs identifiés.");

        // B. Trouver le meilleur couplage
        List<AlgoCouplage.Paire> couplage = AlgoCouplage.trouverCouplageOptimal(this, impairs);

        // C. Modifier le graphe (ajouter les routes virtuelles)
        if(!ModificationGraphe.appliquerCouplage(this, couplage)){ //Si le couplage n'a pas marché
            return false;
        };

        // Calcul du surcoût
        int surcout = 0;
        for (AlgoCouplage.Paire p : couplage) {
            surcout += p.distance;
        }
        tr.setCoutTotal(surcout);
        return true;
    }

    //Permet d'ajouter une route orienté
    public void ajouterRouteOriente(Intersection d, Intersection a, int distance, String nom) {
        Route r = new Route(nom, distance, d, a);
        d.getRoutesSortantes().add(r);
    }


    //Getters
    public List<Intersection> getIntersections() { return intersections; }
    public Intersection getCentreDeTraitement() { return CentreDeTraitement; }
    public String getType() { return type;}
    public Map<String,Route> getRoute() { return routes;}

    //Setters
    public void setCentreDeTraitement(Intersection centreDeTraitement) {
        CentreDeTraitement = centreDeTraitement;
    }
    public void setType(String type) { this.type = type; }
}
