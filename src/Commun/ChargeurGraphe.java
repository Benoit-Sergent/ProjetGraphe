package Commun;

import CreneauxSecteurs.GrapheSecteurs;
import ramassageHabitations.GrapheRoutier;
import ramassagePointDeCollecte.GraphePointDeCollecte;
import ramassagePointDeCollecte.PointDeCollecte;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

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

    // Méthode pour charger le fichier .txt + return un objet GraphePointDeCollecte
    public static GraphePointDeCollecte chargerGraphePDC(File fichier) throws IOException {

        // Objet pour lire le fichier ligne par ligne
        BufferedReader br = new BufferedReader(new FileReader(fichier));

        // Lecture du nombre de sommets
        String ligne = br.readLine();
        int n = Integer.parseInt(ligne.trim()); // convertion de la String en un entier

        // Lecture des noms des sommets
        ligne = br.readLine();
        String[] noms = ligne.split(";"); // on découpe les lignes avec pour séparateur ';' et obtient un tableau
        // On vérifie que le nombre de sommets = longueur du tableau
        if (noms.length != n) {
            throw new IOException("Le nombre de noms (" + noms.length + ") ne correspond pas à n = " + n);
        }

        // Création des objets
        PointDeCollecte depot = new PointDeCollecte(noms[0]); // création du dépôt
        LinkedList<PointDeCollecte> pdcList = new LinkedList<>(); // création d'une liste pour les pdc
        for (int i = 1; i < n; i++) {
            pdcList.add(new PointDeCollecte(noms[i]));
        }

        // Lecture de la matrice
        double[][] matrice = new double[n][n];
        // Première boucle sur les lignes de la matrice
        for (int i = 0; i < n; i++) {
            ligne = br.readLine();
            if (ligne == null) {
                throw new IOException("Ligne " + i + " manquante dans la matrice.");
            }
            // On découpe la ligne avec le séparateur ';'
            String[] valeurs = ligne.split(";");
            if (valeurs.length != n) {
                throw new IOException("Ligne " + i + " : " + valeurs.length + " valeurs, " + n + " attendues.");
            }

            // On parcourt les colonnes
            for (int j = 0; j < n; j++) {
                matrice[i][j] = Double.parseDouble(valeurs[j]);
            }
        }

        br.close();

        // Création du graphe
        GraphePointDeCollecte graphe = new GraphePointDeCollecte();
        graphe.setType("HO1");
        graphe.setCentreDeTraitement(depot);
        graphe.setPointDeCollecte(pdcList);
        graphe.setMatriceDistances(matrice);

        return graphe;
    }

    public static GrapheSecteurs chargerGrapheSecteurs(File fichier) throws IOException{
        return new GrapheSecteurs();
    }
}
