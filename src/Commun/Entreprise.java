package Commun;

import CreneauxSecteurs.GrapheSecteurs;
import ramassageHabitations.GrapheRoutier;
import ramassageHabitations.RechercheItineraireRamassage;
import ramassagePointDeCollecte.GraphePointDeCollecte;

import java.util.List;
import java.util.Scanner;

public class Entreprise extends Utilisateur {
    private GrapheRoutier grapheRoutier = null;
    private GraphePointDeCollecte graphePDC = null;
    private GrapheSecteurs grapheSecteurs = null;

    //Menu de l'entreprise
    public void menuEntreprise(Scanner scanner) {
        while (true) {
            System.out.println("=== Menu Entreprise, identifiant : " + getLogin() + "===");
            System.out.println("1. Calculer un itineraire de ramassage");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1": menuCalculerItineraire(scanner); break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }

    //Choix du calcul d'itinéraire
    public void menuCalculerItineraire(Scanner scanner){
        while (true) {
            System.out.println("=== Choisir le calcul d'itineraire voulu ===");
            System.out.println("1. TSP (Plus Proche Voisin)");
            System.out.println("2. TSP (MST)");
            System.out.println("3. Postier Chinois");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    if(grapheRoutier == null){
                        System.out.println("Le grapheRoutier est vide");
                    }else{
                        Tournee t1 = RechercheItineraireRamassage.PlusProcheVoisinTSP(grapheRoutier);
                        System.out.println(t1.getPointsVisites());
                        System.out.println("Distance : " + t1.getDistanceTotale());
                    }
                    break;

                case "2":
                    if(grapheRoutier == null){
                        System.out.println("Le grapheRoutier est vide");
                    }else{
                        Tournee t1 = RechercheItineraireRamassage.PlusProcheVoisinTSP(grapheRoutier);
                        System.out.println(t1.getPointsVisites());
                        System.out.println("Distance : " + t1.getDistanceTotale());
                    }
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

    //Getters
    public GrapheRoutier getGrapheRoutier() { return grapheRoutier ;}
    public GraphePointDeCollecte getGraphePointDeCollecte() { return graphePDC ;}
    public GrapheSecteurs getGrapheSecteurs() { return grapheSecteurs ;}

    //Setters
    public void setGrapheRoutier(GrapheRoutier grapheRoutier) { this.grapheRoutier = grapheRoutier;}
    public void setGraphePointDeCollecte(GraphePointDeCollecte graphePDC) { this.graphePDC = graphePDC;}
    public void setGrapheSecteurs(GrapheSecteurs grapheSecteurs) { this.grapheSecteurs = grapheSecteurs;}
}
