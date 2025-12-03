package Commun;

import CreneauxSecteurs.GrapheSecteurs;
import ramassageHabitations.GrapheRoutier;
import ramassageHabitations.Intersection;
import ramassageHabitations.Route;
import ramassagePointDeCollecte.GraphePointDeCollecte;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChargeurGraphe {

    public static GrapheRoutier chargerGrapheRoutier(File fichier) throws IOException {
        GrapheRoutier graphe = new GrapheRoutier();

        /**
        Map<Integer, Intersection> mapIntersections = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            boolean lectureIntersections = false;
            boolean lectureRoutes = false;
            int idRoute = 1;

            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty() || ligne.startsWith("#")) {
                    continue;
                }

                // Sections
                if (ligne.equalsIgnoreCase("INTERSECTIONS")) {
                    lectureIntersections = true;
                    lectureRoutes = false;
                    continue;
                }
                if (ligne.equalsIgnoreCase("ROUTES")) {
                    lectureIntersections = false;
                    lectureRoutes = true;
                    continue;
                }

                if (lectureIntersections) {
                    String[] parts = ligne.split("\\s+");
                    if (parts.length < 4) {
                        System.out.println("Ligne INTERSECTION invalide : " + ligne);
                        continue;
                    }
                    int id = Integer.parseInt(parts[0]);
                    String nom = parts[1];
                    double x = Double.parseDouble(parts[2]);
                    double y = Double.parseDouble(parts[3]);

                    Intersection inter = new Intersection(id, nom, x, y);
                    mapIntersections.put(id, inter);
                    graphe.ajouterIntersection(inter);
                } else if (lectureRoutes) {
                    String[] parts = ligne.split("\\s+");
                    if (parts.length < 3) {
                        System.out.println("Ligne ROUTE invalide : " + ligne);
                        continue;
                    }
                    int id1 = Integer.parseInt(parts[0]);
                    int id2 = Integer.parseInt(parts[1]);
                    double distance = Double.parseDouble(parts[2]);

                    Intersection i1 = mapIntersections.get(id1);
                    Intersection i2 = mapIntersections.get(id2);

                    if (i1 == null || i2 == null) {
                        System.out.println("Intersection manquante pour la route : " + ligne);
                        continue;
                    }

                    Route route = new Route(idRoute++, distance, false, i1, i2);
                    graphe.ajouterRoute(route);
                }
            }
        }
        **/
        return graphe;
    }
    public static GraphePointDeCollecte chargerGraphePDC(File fichier) throws IOException{
        return new GraphePointDeCollecte();
    }
    public static GrapheSecteurs chargerGrapheSecteurs(File fichier) throws IOException{
        return new GrapheSecteurs();
    }
}
