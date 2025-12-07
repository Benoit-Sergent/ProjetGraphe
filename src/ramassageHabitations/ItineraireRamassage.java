package ramassageHabitations;

import Commun.Itineraire;
import ramassageHabitations.Graphe.Intersection;
import ramassageHabitations.Graphe.Route;

import java.util.LinkedList;

public class ItineraireRamassage extends Itineraire {
    private LinkedList<Intersection> itineraire;

    //Constructeur
    public ItineraireRamassage(int distance, int quantite_dechet, LinkedList<Intersection> itineraire) {
        super(distance, quantite_dechet);
        this.itineraire = itineraire;
    }
    public ItineraireRamassage(LinkedList<Intersection> itineraire) {
        super(0, 0);
        this.itineraire = itineraire;
    }

    //Getters
    public LinkedList<Intersection> getItineraire() { return itineraire; }

    @Override
    public String toString(){
        return "Données de l'itineraire : Distance = " + getDistance() + ", Dechets = " + getQuantite_dechet() +
                ", Nombre de sommets visité = " + itineraire.size();
    }
}
