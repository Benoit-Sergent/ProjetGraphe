package ramassageHabitations;

import Commun.Tournee;

import java.util.LinkedList;

public class TourneeRamassage extends Tournee {
    private ItineraireRamassage itineraire;
    private LinkedList<DemandeEncombrant> demandeEncombrants = new LinkedList<>();
    private int coutTotal;

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
    public TourneeRamassage(String AlgorithmeUtilise) {
        super(AlgorithmeUtilise);
    }

    //Getters
    public ItineraireRamassage getItineraireRamassage() { return itineraire; }
    public int getCoutTotal() { return coutTotal; }

    //Setters
    public void setItineraire(ItineraireRamassage itineraire) { this.itineraire = itineraire; }
    public void setCoutTotal(int coutTotal) {
        this.coutTotal = coutTotal;
    }


}
