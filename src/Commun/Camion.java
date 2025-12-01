package Commun;

public class Camion {
    private int capaciteMax;

    public Camion(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    //Getters
    public double getCapaciteMax() {
        return capaciteMax;
    }

    @Override
    public String toString() {
        return "Camion{" +
                ", capaciteMax=" + capaciteMax +
                '}';
    }
}
