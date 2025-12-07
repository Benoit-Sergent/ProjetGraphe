package ramassageHabitations;

import ramassageHabitations.Algorithmes.Dijkstra;
import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Algorithmes.*;
import ramassageHabitations.Graphe.Intersection;
import ramassageHabitations.Graphe.Route;

import java.util.*;

public class RechercheItineraireRamassage {
    //Utilisation Dijsktra pour recherche l'itineraire optimale avec une demande d'encombrant
    public static ItineraireRamassage itineraire_encombrant(GrapheRoutier g, Route adresse){
        //Linkedlist temporaire pour faire fonctionner dijkstra
        LinkedList<Intersection> intersection = new LinkedList<>();

        //Itineraire du point de collecte -> debut de l'intersection de la rue
        intersection.add(adresse.getDepart()); //On ajoute la station de départ à la liste
        ItineraireRamassage toDebut = Dijkstra.calculItineraireRamassageDijkstra(g, g.getCentreDeTraitement(), intersection);

        //Itineraire de fin de la rue -> point de collecte
        intersection.clear(); //On vide la liste
        intersection.add(g.getCentreDeTraitement()); //On ajoute le centre de traitement à la liste
        ItineraireRamassage toEnd = Dijkstra.calculItineraireRamassageDijkstra(g, adresse.getArrivee(), intersection);

        //Si on obtient pas de chemin, on retourne null
        if(toDebut == null || toEnd == null){
            return null;
        }

        //Ajout de la distance total et du nombre de dechet total
        int distance_total = toDebut.getDistance() + adresse.getDistance() + toEnd.getDistance();
        int dechet_total = toDebut.getQuantite_dechet() + adresse.getNombre_maisons() + toEnd.getQuantite_dechet();

        //Reconstruction du chemin total
        LinkedList<Intersection> itineraire_total = new LinkedList<>(toDebut.getItineraire());
        itineraire_total.addAll(toEnd.getItineraire());
        ItineraireRamassage trajet_total = new ItineraireRamassage(distance_total, dechet_total, itineraire_total);

        //Affichage des résultats
        System.out.println("\n~~~~~ RESULTAT (Dijkstra) : ~~~~~");
        System.out.println("1. Itineraire centre de traitement -> intersection de fin : ");
        System.out.println(toEnd);
        System.out.println("------------------------------------------------");
        for(int i = 0; i < toEnd.getItineraire().size()-1; i++){
            Intersection debut = toEnd.getItineraire().get(i);
            Intersection fin = toEnd.getItineraire().get(i+1);
            System.out.println(debut + " -> " + fin);
        }

        System.out.println("\n2. Itineraire total : ");
        System.out.println(trajet_total);
        System.out.println("------------------------------------------------");
        for(int i = 0; i < trajet_total.getItineraire().size()-1; i++){
            Intersection debut = trajet_total.getItineraire().get(i);
            Intersection fin = trajet_total.getItineraire().get(i+1);
            System.out.println(debut + " -> " + fin);
        }
        return trajet_total;
    }

    //Heuristique du "plus proche voisin" pour le Problème du Voyageur de Commerce (TSP).
    // À chaque étape, on utilise Dijkstra pour trouver le point non visité le plus proche et on s'y rend.
    public static  ItineraireRamassage itineraire_encombrants(GrapheRoutier g, LinkedList<Route> adresses){
        //Initialisation
        LinkedList<Intersection> tournee = new LinkedList<>(); //Chemin finale de la tournée
        int distanceTotale = 0;
        Intersection debut = g.getCentreDeTraitement();
        tournee.add(debut);

        //Liste des intersections de départ à visiter
        LinkedList<Intersection> intersection_adresses = new LinkedList<>(); //Sommets des rues
        for(Route r : adresses){
            intersection_adresses.add(r.getDepart());//Obtention des sommets des adresses
        }

        //Exploration TSP
        while (!intersection_adresses.isEmpty()) { //Tant que la file n'est pas vide
            // On trouve dijkstra
            ItineraireRamassage ir = Dijkstra.calculItineraireRamassageDijkstra(g, debut, intersection_adresses);
            if (ir == null) { //Si le chemin est null
                System.err.println("Erreur: Impossible de trouver un chemin vers les points restants.");
                return null;
            }
            LinkedList<Intersection> chemin = ir.getItineraire(); //On obtient le chemin
            Route adresse = null;          //On cherche maintenant la rue qu'on souhaite
            for(Route r : adresses){ //On cherche dans les adresses
                if (r.getDepart().equals(chemin.getLast())) { //Si une des routes sortantes est dans la liste
                    adresse = r;        //On attribue cette rue
                    break;
                }
            }

            tournee.addAll(chemin); //On rajoute tout le chemin dans la tournee
            distanceTotale += ir.getDistance() + adresse.getDistance();  //On met la distance totale
            debut = adresse.getArrivee();          //On dit que le nouveau point de départ est la fin de la rue
            intersection_adresses.remove(chemin.getLast()); //On retire le point d'arrivée des sommets à explorer
        }
        //Reconstitution de l'itinéraire
        ItineraireRamassage moitie = new ItineraireRamassage(distanceTotale, 0, tournee);

        //On recherche maintenant le chemin retour
        LinkedList<Intersection> CentreDeTraitement = new LinkedList<>(); //Liste temporaire où on rajoute centre de traitement
        CentreDeTraitement.add(g.getCentreDeTraitement());                //Nécessaire au fonctionnement de Dijkstra
        ItineraireRamassage ir = Dijkstra.calculItineraireRamassageDijkstra(g, debut, CentreDeTraitement);
        if (ir != null) { //Si le chemin n'est pas null
            LinkedList<Intersection> chemin = ir.getItineraire(); //On obtient le chemin
            tournee.addAll(chemin); //On rajoute tout le chemin dans la tournee
            distanceTotale += ir.getDistance(); //On met la distance totale
            tournee.add(g.getCentreDeTraitement()); //On rajoute à la fin le centre de traitement
        } else {
            System.err.println("Erreur: Retour vers le point de collecte impossible.");
        }
        ItineraireRamassage total = new ItineraireRamassage(distanceTotale, 0, tournee);

        //Affichage des résultats
        System.out.println("\n~~~~~ RESULTAT (Dijkstra + TSP) : ~~~~~");
        System.out.println("1. Itineraire centre de traitement -> intersection de fin de la dernière demande : ");
        System.out.println(moitie);
        System.out.println("------------------------------------------------");
        for(int i = 0; i < moitie.getItineraire().size()-1; i++){
            Intersection debut_trajet = moitie.getItineraire().get(i);
            Intersection fin_trajet = moitie.getItineraire().get(i+1);
            System.out.println(debut_trajet + " -> " + fin_trajet);
        }

        System.out.println("\n2. Itineraire total : ");
        System.out.println(total);
        System.out.println("------------------------------------------------");
        for(int i = 0; i < total.getItineraire().size()-1; i++){
            Intersection debut_trajet = total.getItineraire().get(i);
            Intersection fin_trajet = total.getItineraire().get(i+1);
            System.out.println(debut_trajet + " -> " + fin_trajet);
        }

        return total;
    }

    //ALGORITHMES : Postier Chinois (via AlgoCouplage et AlgoHierholzer).
    // 1. On identifie les sommets de degré impair.
    // 2. On utilise AlgoCouplage pour trouver les chemins à dupliquer afin de rendre le graphe eulérien.
    // 3. On utilise AlgoHierholzer pour trouver le circuit eulérien final sur le graphe modifié.

    public static TourneeRamassage gererCollectePoubelles(GrapheRoutier graphe) {
        //On Vérifie que le graphe soit Eulérien
        //Message d'accueil qui vérife
        TourneeRamassage tr = new TourneeRamassage("AlgoHierholzer");
        System.out.println(">> Calcul de la tournée en cours...");

        // 1. Vérification si le graphe est Eulérien
        if (graphe.estEulerien()) {
            System.out.println("   Graphe Eulérien détecté (Cas Idéal).");
        } else {
            System.out.println("   Graphe non Eulérien (Cas Réel). Application du correctif...");
            if(!graphe.devenirEulerien(tr)){
               return null;
            };
        }

        // 2. Calcul du circuit final (Hierholzer)
        ItineraireRamassage ir = new ItineraireRamassage(AlgoHierholzer.trouverCircuit(graphe, graphe.getCentreDeTraitement()));
        tr.setItineraire(ir);
        System.out.println(">> Tournée calculée avec succès !");

        List<Intersection> chemin = tr.getItineraireRamassage().getItineraire();

        if (chemin == null || chemin.isEmpty()) {
            System.out.println("Erreur : Impossible de calculer l'itinéraire.");
        } else {
            //Affichage des résultats
            System.out.println("\n~~~~~ RESULTAT (Postier Chinois) : ~~~~~");
            System.out.println("1. Itineraire centre de traitement -> intersection de fin : ");
            System.out.println(ir);
            System.out.println("------------------------------------------------");
            for(int i = 0; i < ir.getItineraire().size()-1; i++){
                Intersection debut = ir.getItineraire().get(i);
                Intersection fin = ir.getItineraire().get(i+1);
                System.out.println(debut + " -> " + fin);
            }
            if (tr.getCoutTotal() > 0) {
                System.out.println("Surcoût dû aux retours (arêtes dupliquées) : " + tr.getCoutTotal() + " m");
            } else {
                System.out.println("Trajet optimal sans répétition (Graphe Eulérien parfait).");
            }
        }
        return tr;
    }
}