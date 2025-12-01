package Commun;

import ramassageHabitations.Intersection;
import ramassagePointDeCollecte.PointDeCollecte;

public class CentreDeTraitement {
    private Intersection Intersection;
    private PointDeCollecte pointDeCollecte;

    //Getters
    public Intersection getIntersection() {
        return Intersection;
    }
    public PointDeCollecte getPointDeCollecte() { return pointDeCollecte; }

    //Setters
    public void setIntersection(Intersection Intersection) {
        this.Intersection = Intersection;
    }
    public void setPointDeCollecte(PointDeCollecte pointDeCollecte) { this.pointDeCollecte = pointDeCollecte; }
}
