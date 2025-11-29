import java.util.List;

public class MSTTSP implements AlgorithmeItineraire {

    @Override
    public Tournee calculerTournee(GrapheRoutier graphe, Depot depot, List<PointCollecte> points) {
        //Implémentation de l'approche MST + parcours préfixe + shortcutting
        System.out.println("Calcul de tournée avec l'approche MST (TSP)...");
        return new Tournee(null, depot);
    }
}
