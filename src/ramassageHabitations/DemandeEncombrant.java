package ramassageHabitations;

import ramassageHabitations.Graphe.Route;

public class DemandeEncombrant {
    private Route rue;

    //Constructeur
    public DemandeEncombrant(Route rue){
        this.rue = rue;
    }

    //Getters
    public Route getRue(){ return rue;}

    @Override
    public String toString(){
        return "Rue : " + rue.getNom();
    }
}
