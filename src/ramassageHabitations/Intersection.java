package ramassageHabitations;

import java.util.LinkedList;

public class Intersection {
    private String nom;
    private LinkedList<Route> routes_sortantes;

    //Getters
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                ", nom='" + nom + '\'' +
                '}';
    }
}
