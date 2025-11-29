import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournee {
    private List<PointCollecte> pointsVisites = new ArrayList<>();
    private double distanceTotale;
    private double chargeTotale;
    private Camion camion;
    private Depot depot;

    public Tournee(Camion camion, Depot depot) {
        this.camion = camion;
        this.depot = depot;
    }

    public void ajouterPoint(PointCollecte point) {
        pointsVisites.add(point);
    }

    public List<PointCollecte> getPointsVisites() {
        return Collections.unmodifiableList(pointsVisites);
    }

    public double getDistanceTotale() {
        return distanceTotale;
    }

    public void setDistanceTotale(double distanceTotale) {
        this.distanceTotale = distanceTotale;
    }

    public double getChargeTotale() {
        return chargeTotale;
    }

    public void setChargeTotale(double chargeTotale) {
        this.chargeTotale = chargeTotale;
    }

    public Camion getCamion() {
        return camion;
    }

    public Depot getDepot() {
        return depot;
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "pointsVisites=" + pointsVisites +
                ", distanceTotale=" + distanceTotale +
                ", chargeTotale=" + chargeTotale +
                ", camion=" + camion +
                ", depot=" + depot +
                '}';
    }
}
