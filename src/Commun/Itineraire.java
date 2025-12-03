package Commun;

public abstract class Itineraire {
    private int distance;
    private int quantite_dechet;

    //Getters
    public int getDistance() { return distance;}
    public int getQuantite_dechet() { return quantite_dechet;}

    //Setters
    public void setDistance(int distance) { this.distance = distance; }
    public void setQuantite_dechet(int quantite_dechet) { this.quantite_dechet = quantite_dechet; }
}
