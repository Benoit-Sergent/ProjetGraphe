package ramassageHabitations.Algorithmes;

import ramassageHabitations.Graphe.*;
import ramassageHabitations.ItineraireRamassage;

import java.util.*;

public class Dijkstra {

    //Resultat Dijkstra
    public static class DijkstraResultat {
        final Map<Intersection,Integer> distances;
        final Map<Intersection,Intersection> predecesseurs;
        DijkstraResultat(Map<Intersection,Integer> d, Map<Intersection,Intersection> p) {
            this.distances = d;
            this.predecesseurs = p;
        }

        // Récupère la distance minimale vers une destination
        public int getDistance(Intersection arrivee) {
            return distances.getOrDefault(arrivee, Integer.MAX_VALUE);
        }
    }

    //Renvoie le résultat de Dijkstra
    public static DijkstraResultat Dijkstra(GrapheRoutier graphe, Intersection debut) {
        Map<Intersection, Integer> distances = new HashMap<>();
        Map<Intersection, Intersection> predecesseurs = new HashMap<>();

        // File de priorité pour explorer toujours le sommet le plus proche en premier
        // On compare les intersections selon leur distance actuelle connue
        PriorityQueue<Intersection> filePriorite = new PriorityQueue<>(
                Comparator.comparingInt(i -> distances.getOrDefault(i, Integer.MAX_VALUE))
        );

        // 1. Initialisation
        for (Intersection i : graphe.getIntersections()) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(debut, 0);
        filePriorite.add(debut);

        // 2. Boucle principale
        while (!filePriorite.isEmpty()) {
            // Récupère le sommet le plus proche
            Intersection u = filePriorite.poll();
            int distanceU = distances.get(u);

            // Si la distance est infinie, le reste du graphe est inatteignable
            if (distanceU == Integer.MAX_VALUE) break;

            // Relâchement des arcs sortants
            for (Route route : u.getRoutesSortantes()) {
                Intersection v = route.getArrivee();
                int poids = route.getDistance();
                int distanceAlternative = distances.get(u) + poids;

                // Si on a trouvé un chemin plus court vers v
                if (distanceAlternative < distances.get(v)) {
                    distances.put(v, distanceAlternative);
                    predecesseurs.put(v, u);

                    // Mise à jour de la position de v dans la file de priorité
                    // (Suppression et réinsertion pour forcer le tri)
                    filePriorite.remove(v);
                    filePriorite.add(v);
                }
            }
        }
        return new DijkstraResultat(distances, predecesseurs);
    }

    //Fonction qui calcul à l'aide de Dijkstra l'itineraire le plus court vers 1 ou plusieurs points
    public static ItineraireRamassage calculItineraireRamassageDijkstra(GrapheRoutier graphe, Intersection debut, LinkedList<Intersection> fin) {
        DijkstraResultat res = Dijkstra(graphe, debut);
        Map<Intersection,Integer> distances = res.distances;
        Map<Intersection,Intersection> predecesseurs = res.predecesseurs;

        //On compare les distances pour voir quel fin est plus proche du point de debut
        Intersection plusProche = null;
        double distanceMin = Integer.MAX_VALUE;

        //On recréer maintenant le chemin
        for(Intersection i : fin){
            int distance = distances.get(i);
            if (distance < distanceMin) {
                distanceMin = distance;
                plusProche = i;
            }
        }
        //Si le chemin n'est pas trouvée
        if(plusProche == null){
            return  null;
        }
        // Si la destination est inatteignable ou inconnue
        if (distances.get(plusProche) == Integer.MAX_VALUE) {
            return null; // Retourne un itineraire null
        }

        //Reconstitution du chemin
        LinkedList<Intersection> chemin = new LinkedList<>();

        //se prepare à retracer le chemin en partant de la fin
        Intersection etape = plusProche;
        chemin.add(etape);

        // On remonte via les prédécesseurs jusqu'au départ
        while (predecesseurs.containsKey(etape)) {
            etape = predecesseurs.get(etape);
            chemin.addFirst(etape);
        }

        //On récupère la distance totale de l'itineraire
        int distance_total = distances.get(plusProche);

        //On retourne l'itineraire créer
        return new ItineraireRamassage(distance_total, 0,  chemin);
    }
}