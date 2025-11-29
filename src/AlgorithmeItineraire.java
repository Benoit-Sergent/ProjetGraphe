import java.util.List;

public interface AlgorithmeItineraire {
    Tournee calculerTournee(GrapheRoutier graphe, Depot depot, List<PointCollecte> points);
}
