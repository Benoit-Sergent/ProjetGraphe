package Commun;

public class Camion {
    private int id;
    private double capaciteMax;
    private Depot depot;

    public Camion(int id, double capaciteMax, Depot depot) {
        this.id = id;
        this.capaciteMax = capaciteMax;
        this.depot = depot;
    }

    public int getId() {
        return id;
    }

    public double getCapaciteMax() {
        return capaciteMax;
    }

    public Depot getDepot() {
        return depot;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", capaciteMax=" + capaciteMax +
                ", depot=" + depot +
                '}';
    }
}
