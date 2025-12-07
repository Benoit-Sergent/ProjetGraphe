package CreneauxSecteurs;

import java.util.*;

public class AlgorithmeColorationSecteurs {


    public static void Colorisation(GrapheSecteurs graphe) throws NullPointerException {
        //Si le graphe n'est pas chargé, on renvoie une exception
        if (graphe == null) { throw new NullPointerException("ramassagePointDeCollecte.Graphe non chargé");}

        // Création d'un tableau pour afficher les couleurs : dans ce cas les jours de la semaine
        String[] Jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

        // Création d'une liste pour les degrés des sommets
        List<Secteur> liste_secteur = new LinkedList<>(graphe.getSecteurs());
        // Tri décroissant des sommets en fonction de leurs degrés (nombre de liens soit nombre de sommets adjacent)
        liste_secteur.sort(Comparator.comparingInt(s -> ((Secteur) s).getSecteursVoisins().size()).reversed());

        //Coloration Welsh-Powell
        for (Secteur s : liste_secteur) { // Pour tous les sommets (ordre décroissant)

            // couleurs disponibles
            Map<String, Boolean> disponible = new HashMap<>(); //Création de map des jours
            for (String j : Jours)
                disponible.put(j, true);    //On met les jours de Jours et à true, pour dire qu'ils sont libre

            // rendre indisponibles les couleurs des voisins
            for (Secteur voisin : s.getSecteursVoisins()) { //Pour chaque voisin
                String jourVoisin = voisin.getJour();
                if (jourVoisin != null) {
                    disponible.put(jourVoisin, false);  //On met la couleur à false pour exprimer que c'est pris
                }
            }

            // choisir la 1ère couleur disponible
            for (String j : Jours) {    //Pour chaque jour de Jours
                if (disponible.get(j)) {   //On regarde si la couleur est disponible
                    s.setJour(j);           //On met la couleur
                    break;                  //On arrête
                }
                s.setJour("Erreur, aucun jour dispo"); //Si aucun jour n'est attribué, il y a une erreur
            }
        }

        //Affichage
        System.out.println();
        for (Secteur s : graphe.getSecteurs()) { // Parcours des sommets
            System.out.println(s.getNom() + " --> " + s.getJour()); // Affichage d'un jour de la semaine pour un sommet
        }
    }
}
