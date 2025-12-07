package ramassageHabitations.Algorithmes;

import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Graphe.Intersection;
import ramassageHabitations.ItineraireRamassage;

import java.util.*;

public class AlgoCouplage {

    /**
     * Représente une paire de sommets à relier.
     */
    public static class Paire {
        public Intersection a;
        public Intersection b;
        public int distance;

        public Paire(Intersection a, Intersection b, int distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;
        }
    }

    /**
     * Trouve le couplage optimal (somme des distances minimale) entre les sommets impairs.
     * @param graphe Le graphe routier.
     * @param sommetsImpairs La liste des sommets ayant un degré impair.
     * @return La liste des paires optimales.
     */
    public static List<Paire> trouverCouplageOptimal(GrapheRoutier graphe, List<Intersection> sommetsImpairs) {
        // Pré-calcul de toutes les distances entre sommets impairs pour éviter de relancer Dijkstra trop souvent
        Map<String, Integer> cacheDistances = new HashMap<>();

        for (int i = 0; i < sommetsImpairs.size(); i++) {
            Intersection depart = sommetsImpairs.get(i);
            Dijkstra.DijkstraResultat res = Dijkstra.Dijkstra(graphe, depart);

            for (int j = 0; j < sommetsImpairs.size(); j++) {
                if (i == j) continue;
                Intersection arrivee = sommetsImpairs.get(j);
                String cle = genererCle(depart, arrivee);
                cacheDistances.put(cle, res.getDistance(arrivee));
            }
        }

        return resoudreRecursif(sommetsImpairs, cacheDistances);
    }

    /**
     * Algorithme récursif pour tester les combinaisons de paires.
     */
    private static List<Paire> resoudreRecursif(List<Intersection> sommets, Map<String, Integer> distances) {
        // Cas de base : plus de sommets à apparier
        if (sommets.isEmpty()) {
            return new ArrayList<>();
        }

        Intersection premier = sommets.get(0);
        List<Paire> meilleurResultat = null;
        int meilleureDistanceTotale = Integer.MAX_VALUE;

        // On essaye de marier le "premier" sommet avec tous les autres sommets restants
        for (int i = 1; i < sommets.size(); i++) {
            Intersection autre = sommets.get(i);

            // 1. On forme une paire
            String cle = genererCle(premier, autre);
            int dist = distances.getOrDefault(cle, Integer.MAX_VALUE);

            if (dist == Integer.MAX_VALUE) continue; // Pas de chemin possible

            // 2. On prépare la liste des sommets restants (sans 'premier' ni 'autre')
            List<Intersection> reste = new ArrayList<>(sommets);
            reste.remove(i); // Remove 'autre' first (index is correct because i >= 1)
            reste.remove(0); // Remove 'premier'

            // 3. Appel récursif pour apparier le reste
            List<Paire> resultatPartiel = resoudreRecursif(reste, distances);

            // 4. Calcul du coût total de cette combinaison
            if (resultatPartiel != null) {
                int totalDist = dist;
                for (Paire p : resultatPartiel) totalDist += p.distance;

                // 5. Si c'est mieux que ce qu'on a trouvé avant, on garde
                if (totalDist < meilleureDistanceTotale) {
                    meilleureDistanceTotale = totalDist;
                    meilleurResultat = new ArrayList<>();
                    meilleurResultat.add(new Paire(premier, autre, dist));
                    meilleurResultat.addAll(resultatPartiel);
                }
            }
        }
        return meilleurResultat;
    }

    private static String genererCle(Intersection a, Intersection b) {
        // Génère une clé unique pour la paire, peu importe l'ordre (A-B ou B-A)
        if (a.getNom().compareTo(b.getNom()) < 0) {
            return a.getNom() + "-" + b.getNom();
        } else {
            return b.getNom() + "-" + a.getNom();
        }
    }
}