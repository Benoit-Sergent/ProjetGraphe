import Commun.CentreDeTraitement;
import ramassageHabitations.RechercheItineraireRamassage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.HeadlessException;

public class ApplicationConsole {

    public static void main(String[] args) {
        ApplicationConsole app = new ApplicationConsole();
        app.afficherMenuPrincipal();
    }

    public void afficherMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        GrapheRoutier graphe = null;

        while (true) {
            System.out.println("=== Système de collecte des déchets ===");
            System.out.println("1. Charger un fichier de graphe");
            System.out.println("2. Menu Collectivité");
            System.out.println("3. Menu Entreprise de collecte");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    try {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Sélectionnez un fichier de graphe (.txt)");
                        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers texte (*.txt)", "txt"));

                        int resultat = fileChooser.showOpenDialog(null);

                        if (resultat == JFileChooser.APPROVE_OPTION) {
                            File fichier = fileChooser.getSelectedFile();
                            try {
                                graphe = ChargeurGraphe.chargerDepuisFichier(fichier);
                                System.out.println("Graphe chargé avec succès.");
                            } catch (Exception e) {
                                System.out.println("Erreur lors du chargement : " + e.getMessage());
                                graphe = null;
                            }
                        } else {
                            System.out.println("Sélection annulée.");
                        }
                    } catch (HeadlessException e) {
                        System.out.println("Mode graphique indisponible.");
                        System.out.print("Chemin du fichier : ");
                        String chemin = scanner.nextLine();
                        File fichier = new File(chemin);

                        if (!fichier.exists()) {
                            System.out.println("Le fichier n'existe pas.");
                        } else {
                            try {
                                graphe = ChargeurGraphe.chargerDepuisFichier(fichier);
                                System.out.println("Graphe chargé avec succès.");
                            } catch (Exception ex) {
                                System.out.println("Erreur lors du chargement : " + ex.getMessage());
                                graphe = null;
                            }
                        }
                    }
                    break;

                case "2":
                    if (graphe == null) System.out.println("Veuillez d'abord charger un graphe.");
                    else menuCollectivite(scanner, graphe);
                    break;

                case "3":
                    if (graphe == null) System.out.println("Veuillez d'abord charger un graphe.");
                    else menuEntreprise(scanner, graphe);
                    break;

                case "0":
                    System.out.println("Au revoir.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    public void menuCollectivite(Scanner scanner, GrapheRoutier graphe) {
        System.out.println("=== Menu Collectivité ===");

        List<PointCollecte> points = GrapheExemple.creerPointsCollecte(graphe);

        List<Secteur> listeSecteurs = new ArrayList<>();
        Secteur s1 = new Secteur(1, "Centre", 5.0, -1);
        Secteur s2 = new Secteur(2, "Nord", 6.0, -1);
        Secteur s3 = new Secteur(3, "Sud", 4.0, -1);

        s1.ajouterVoisin(s2);
        s1.ajouterVoisin(s3);
        s2.ajouterVoisin(s1);
        s3.ajouterVoisin(s1);

        listeSecteurs.add(s1);
        listeSecteurs.add(s2);
        listeSecteurs.add(s3);

        while (true) {
            System.out.println("\n1. Afficher les points de collecte");
            System.out.println("2. Afficher les secteurs");
            System.out.println("3. Colorer les secteurs");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    for (PointCollecte p : points) System.out.println("- " + p);
                    break;

                case "2":
                    for (Secteur s : listeSecteurs)
                        System.out.println(s.getNom() + " | couleur : " + s.getCouleur());
                    break;

                case "3":
                    AlgorithmeColorationSecteurs algo = new AlgorithmeColorationSecteurs();
                    algo.colorerSecteurs(listeSecteurs);
                    for (Secteur s : listeSecteurs)
                        System.out.println(s.getNom() + " → Jour " + s.getCouleur());
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Choix inv");
            }
        }
    }

    public void menuEntreprise(Scanner scanner, GrapheRoutier graphe) {
        System.out.println("=== Menu Entreprise de collecte ===");

        CentreDeTraitement centreDeTraitement = GrapheExemple.creerDepot(graphe);
        Camion camion = GrapheExemple.creerCamion(centreDeTraitement);
        List<PointCollecte> points = GrapheExemple.creerPointsCollecte(graphe);

        while (true) {
            System.out.println("\n1. TSP (Plus Proche Voisin)");
            System.out.println("2. TSP (MST)");
            System.out.println("3. Postier Chinois");
            System.out.println("4. Afficher les points de collecte");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    Tournee t1 = RechercheItineraireRamassage.PlusProcheVoisinTSP(graphe, centreDeTraitement, points);
                    System.out.println(t1.getPointsVisites());
                    System.out.println("Distance : " + t1.getDistanceTotale());
                    break;

                case "2":
                    Tournee t2 = RechercheItineraireRamassage.MSTTSP(graphe, centreDeTraitement, points);
                    System.out.println(t2.getPointsVisites());
                    System.out.println("Distance : " + t2.getDistanceTotale());
                    break;

                case "3":
                    Tournee t3 = RechercheItineraireRamassage.postierChinois(graphe, centreDeTraitement, camion);
                    System.out.println("Distance : " + t3.getDistanceTotale());
                    break;

                case "4":
                    for (PointCollecte p : points) System.out.println("- " + p);
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Choix inv.");
            }
        }
    }
}
