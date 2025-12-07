package Commun;

import CreneauxSecteurs.GrapheSecteurs;
import CreneauxSecteurs.Secteur;
import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Graphe.Intersection;
import ramassageHabitations.Graphe.Route;
import ramassagePointDeCollecte.Graphe.GraphePointDeCollecte;
import ramassagePointDeCollecte.Graphe.PointDeCollecte;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class ChargeurFichier {

    //Fonction qui charge un fichier
    public static File chargerFichier(Scanner scanner, String objet_nom){
        //Ouvre le fichier
        File fichier = null;

        //Lecture fichier
        try {
            // Le "." ouvre la fenêtre dans le dossier racine du projet actuel
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setDialogTitle("Sélectionnez le fichier de " + objet_nom +  "(.txt)");

            // Filtre pour ne montrer que les fichiers .txt
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers Texte (*.txt)", "txt");
            fileChooser.setFileFilter(filter);

            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                fichier = fileChooser.getSelectedFile();
                System.out.println("Fichier sélectionné avec succès. Nom : " +fichier.getName());
            }else {
                System.out.println("Sélection annulée.");
            }
            //Cas où l'interface graphique ne fonctionne pas
        }catch (HeadlessException e){
            System.out.println("Mode graphique indisponible.");
            System.out.print("Chemin du fichier : ");
            String chemin = scanner.nextLine();

            fichier = new File(chemin);
            if (!fichier.exists()) {
                System.out.println("Le fichier n'existe pas.");
            } else {
                System.out.println("Fichier selectionné avec succès");
            }
        }
        return fichier;
    }

    public static GrapheRoutier chargerGrapheRoutier(File fichier) throws IOException {
        GrapheRoutier graphe = new GrapheRoutier();
        //Dictionnaire pour éviter les doublons de sommet et possibilité de les modifier
        HashMap<String, Intersection> dico_intersection = new HashMap<>();

        Scanner sc = new Scanner(fichier);
        while (sc.hasNextLine()) { // Lecture du fichier ligne par ligne
            // Découpage de la ligne selon la virgule
            String[] parts = sc.nextLine().split(",");

            if (parts.length >= 4) { // Vérification du format : Source, Destination, Distance, Nom
                String src = parts[0]; //Nom source
                String dst = parts[1]; //Nom Destination
                int dist = Integer.parseInt(parts[2]); //Taille distance
                String nom = parts[3]; //Nom rue

                // Ajout des sommets au graphe, s'ils n'y sont pas déjà
                dico_intersection.putIfAbsent(src, new Intersection(src));
                dico_intersection.putIfAbsent(dst, new Intersection(dst));

                //On obtient les sommets du graphe (qui viennent d'être créer ou non)
                Intersection d = dico_intersection.get(src);
                Intersection a = dico_intersection.get(dst);

                //On crée une nouvelle route et on l'ajoute au graphe ainsi qu'en sortie du sommet de départ
                Route r = new Route(nom, dist, d, a);
                d.getRoutesSortantes().add(r);
                graphe.getRoute().put(nom, r);
            }
        }
        //On ajoute la liste d'intersection obtenu à la notre liste
        graphe.getIntersections().addAll((dico_intersection.values()));
        //On attribue le centre de traitement
        graphe.setCentreDeTraitement(dico_intersection.get("P.C"));
        graphe.setType(fichier.getName().split("_")[1]);
        sc.close();
        return graphe;
    }

    // Méthode pour charger le fichier .txt + return un objet GraphePointDeCollecte
    public static GraphePointDeCollecte chargerGraphePDC(File fichier) throws IOException {

        // Objet pour lire le fichier ligne par ligne
        Scanner sc = new Scanner(fichier);

        // Lecture du nombre de sommets
        String ligne = sc.nextLine();
        int n = Integer.parseInt(ligne); // convertion de la String en un entier

        // Lecture des noms des sommets
        ligne = sc.nextLine();
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
            ligne = sc.nextLine();
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

        sc.close();

        // Création du graphe
        GraphePointDeCollecte graphe = new GraphePointDeCollecte();
        graphe.setType("HO1");
        graphe.setCentreDeTraitement(depot);
        graphe.setPointDeCollecte(pdcList);
        graphe.setMatriceDistances(matrice);

        return graphe;
    }
    public static GrapheSecteurs chargerGrapheSecteurs(File fichier) throws IOException {
        //Création du graphe
        GrapheSecteurs graphe = new GrapheSecteurs();
        // Lit le fichier texte
        Scanner sc = new Scanner(fichier);
        //Création d'un map pour contenir les secteurs et affecter les arrêtes
        Map<String, Secteur> mapSecteurs = new HashMap<>();

        //Chargement des sommets
        int Nombre_Sommet = Integer.parseInt(sc.nextLine());    // Lecture du nombre de sommet + conversion
        for (int i = 0; i < Nombre_Sommet; i++) {               // Parcours les sommets de 0 à Nombre_Sommet - 1
            String[] ligne = sc.nextLine().split(",");    // Lecture de la ligne et on la coupe pour obtenir infos
            String nom = ligne[0];                              // première partie: nom
            int quantite_estime = Integer.parseInt(ligne[1]);   // deuxieme partie: quantite
            Secteur secteur = new Secteur(nom, quantite_estime);// Création Secteur
            graphe.getSecteurs().add(secteur);                  // On rajoute le secteur au graphe
            mapSecteurs.put(nom, secteur);                      // On rajoute dans un map pour les arcs
        }

        //Chargement des Arcs
        int Nombre_Arc = Integer.parseInt(sc.nextLine());       //Lecture du nombre d'arc + conversion
        for (int i = 0; i < Nombre_Arc; i++) {                  // Parcours les sommets de 0 à Nombre_Arc - 1
            String[] ligne = sc.nextLine().split(" ");    // Lecture de la ligne et on la coupe pour obtenir infos
            String depart = ligne[0];                           // Première ligne : depart
            String arrive = ligne[1];                           // Deuxième ligne : depart

            Secteur s1 = mapSecteurs.get(depart);               // On récupère s1 depuis map
            Secteur s2 = mapSecteurs.get(arrive);               // On récupère s2 depuis map

            s1.getSecteursVoisins().add(s2);                    // Ajout des voisins (non orienté donc dans les deux sens)
            s2.getSecteursVoisins().add(s1);                    // Ajout des voisins (non orienté donc dans les deux sens)
        }

        sc.close(); // Fermeture du scanner
        return graphe;
    }
}
