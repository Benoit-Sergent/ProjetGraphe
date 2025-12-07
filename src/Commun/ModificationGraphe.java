package Commun;

import ramassageHabitations.Algorithmes.Dijkstra;
import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Graphe.Intersection;
import ramassageHabitations.Graphe.Route;
import ramassageHabitations.Algorithmes.AlgoCouplage.*;

import java.util.LinkedList;
import java.util.List;

import static ramassageHabitations.Algorithmes.Dijkstra.*;

public class ModificationGraphe {

    /**
     * Modifie le graphe en ajoutant des routes virtuelles correspondant aux chemins les plus courts
     * entre les paires de sommets fournies.
     */
    public static boolean appliquerCouplage(GrapheRoutier graphe, List<Paire> couplage) {
        System.out.println(">> Modification du graphe : Ajout des arêtes virtuelles...");

        for (Paire paire : couplage) {
            // 1. Retrouver le chemin complet (la suite de rues) entre A et B
            LinkedList<Intersection> liste = new LinkedList<>();
            liste.add(paire.b);
            List<Intersection> chemin = calculItineraireRamassageDijkstra(graphe, paire.a, liste).getItineraire();
            if(chemin == null){
                System.out.println("Erreur impossible dans le chemin de la paire");
                return false;
            }
            // 2. Parcourir le chemin et dupliquer chaque arête
            for (int i = 0; i < chemin.size() - 1; i++) {
                Intersection u = chemin.get(i);
                Intersection v = chemin.get(i + 1);

                // Trouver la route existante pour récupérer ses propriétés (nom, distance)
                Route routeOriginale = trouverRoute(u, v);

                if (routeOriginale != null) {
                    // On ajoute une nouvelle route identique (virtuelle)
                    // Cela augmente le degré de U et V de 1, changeant leur parité
                    graphe.ajouterRouteOriente(u, v, routeOriginale.getDistance(), routeOriginale.getNom() + " (Retour)");

                    // Si le graphe est non orienté, il faut aussi ajouter le retour pour garder la cohérence
                    // (Note: dans notre modèle 'GrapheRoutier', ajouterRouteOriente suffit si on traite le graphe comme orienté pour le cycle)
                    // Mais pour Hierholzer sur graphe mixte, on ajoute souvent la paire inverse aussi si c'était une rue double sens.
                    // Pour simplifier ici, on ajoute juste l'arc nécessaire au transit.
                }
            }
            System.out.println("   + Chemin ajouté entre " + paire.a.getNom() + " et " + paire.b.getNom());
        }
        return true;
    }

    private static Route trouverRoute(Intersection source, Intersection dest) {
        for (Route r : source.getRoutesSortantes()) {
            if (r.getArrivee().equals(dest)) {
                return r;
            }
        }
        return null;
    }
}