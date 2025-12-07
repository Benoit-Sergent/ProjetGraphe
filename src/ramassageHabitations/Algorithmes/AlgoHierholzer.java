package ramassageHabitations.Algorithmes;

import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Graphe.Intersection;
import ramassageHabitations.Graphe.Route;

import java.util.*;

public class AlgoHierholzer {
    /**
     * Trouve un circuit eulérien dans le graphe (parcourt toutes les arêtes une seule fois).
     * @param graphe Le graphe à parcourir (doit être eulérien).
     * @param depart Le point de départ du camion.
     * @return La liste ordonnée des intersections formant le circuit.
     */
    public static LinkedList<Intersection> trouverCircuit(GrapheRoutier graphe, Intersection depart) {
        // 1. Copie des arêtes pour pouvoir les supprimer au fur et à mesure de l'exploration
        // Cela évite de détruire le graphe original
        Map<String, List<Route>> adjacences = new HashMap<>();

        for (Intersection i : graphe.getIntersections()) {
            adjacences.put(i.getNom(), new ArrayList<>(i.getRoutesSortantes()));
        }

        // 2. Structures pour l'algorithme
        // La pile sert à avancer tant qu'on peut (DFS)
        Stack<Intersection> pile = new Stack<>();
        // La liste finale stocke le circuit construit à l'envers
        LinkedList<Intersection> circuitFinal = new LinkedList<>();

        pile.push(depart);

        // 3. Boucle principale de l'algorithme de Hierholzer
        while (!pile.isEmpty()) {
            Intersection u = pile.peek(); // On regarde le sommet actuel sans le dépiler
            List<Route> routesDisponibles = adjacences.get(u.getNom());

            if (routesDisponibles != null && !routesDisponibles.isEmpty()) {
                // CAS 1 : Il reste des chemins à explorer depuis ce sommet
                // On choisit la première route disponible
                Route routePrise = routesDisponibles.getFirst();
                Intersection v = routePrise.getArrivee();

                // On "supprime" l'arête aller de notre copie du graphe
                routesDisponibles.removeFirst();

                // IMPORTANT POUR GRAPHES NON ORIENTÉS (Hypothèse HO1) :
                // Si on emprunte A->B, on consomme aussi la possibilité de faire B->A pour cette rue.
                // Il faut donc supprimer l'arête inverse correspondante.
                supprimerAreteRetour(adjacences, v, u, routePrise.getNom());

                // On empile le voisin pour continuer d'avancer
                pile.push(v);
            } else {
                // CAS 2 : On est bloqué (plus de routes sortantes non visitées)
                // Cela signifie qu'on a terminé un sous-circuit.
                // On ajoute le sommet au circuit final et on dépile pour revenir en arrière.
                circuitFinal.add(pile.pop());
            }
        }

        // 4. Le circuit est construit à l'envers (du dernier visité au premier), on l'inverse.
        Collections.reverse(circuitFinal);
        return circuitFinal;
    }

    /**
     * Supprime l'arête inverse (B->A) quand on vient de traverser (A->B).
     * Nécessaire pour les graphes non orientés ou mixtes où une rue est bidirectionnelle.
     */
    private static void supprimerAreteRetour(Map<String, List<Route>> adj, Intersection source, Intersection cible, String nomRue) {
        List<Route> routes = adj.get(source.getNom());
        if (routes == null) return;

        Route aSupprimer = null;
        for (Route r : routes) {
            // On cherche la route qui repart de l'arrivée (source ici) vers l'origine (cible ici)
            // et qui porte le même nom de rue.
            if (r.getArrivee().getNom().equals(cible.getNom()) && r.getNom().equals(nomRue)) {
                aSupprimer = r;
                break;
            }
        }

        // Si on la trouve, on la supprime pour ne pas la réemprunter immédiatement
        if (aSupprimer != null) {
            routes.remove(aSupprimer);
        }
    }
}