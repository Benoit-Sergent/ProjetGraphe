import Commun.CentreDeTraitement;

import java.util.List;

public interface AlgorithmeItineraire {
    Tournee calculerTournee(GrapheRoutier graphe, CentreDeTraitement centreDeTraitement, List<PointCollecte> points);
}
