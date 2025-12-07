package ramassageHabitations;

import Commun.Tournee;

import java.util.LinkedList;

public class TourneeRamassage extends Tournee {
    private ItineraireRamassage itineraire;
    private LinkedList<DemandeEncombrant> demandeEncombrants = new LinkedList<>();

    //Constructeur
    public TourneeRamassage(String AlgorithmeUtilise, ItineraireRamassage itineraire, LinkedList<DemandeEncombrant> demandeEncombrants) {
        super(AlgorithmeUtilise);
        this.demandeEncombrants = demandeEncombrants;
        this.itineraire = itineraire;
    }
    public TourneeRamassage(String AlgorithmeUtilise, ItineraireRamassage itineraire, DemandeEncombrant demandeEncombrants) {
        super(AlgorithmeUtilise);
        this.demandeEncombrants.add(demandeEncombrants);
        this.itineraire = itineraire;
    }
}
