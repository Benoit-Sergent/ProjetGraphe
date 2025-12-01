package Commun;

import CreneauxSecteurs.GrapheSecteurs;
import ramassageHabitations.GrapheRoutier;
import ramassageHabitations.RechercheItineraireRamassage;
import ramassagePointDeCollecte.GraphePointDeCollecte;
import ramassagePointDeCollecte.RechercheItinerairePointDeCollecte;

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
    private void menuCalculerItineraire(Scanner scanner){
        while (true) {
            System.out.println("=== Choisir le type d'itineraire de ramassage ===");
            System.out.println("1. Ramassage encombrants");
            System.out.println("2. Ramassage poubelles");
            System.out.println("3. Ramassage point de collecte");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": menuCalculerEncombrants(scanner);break;
                case "2": menuCalculerPoubelle(scanner); break;
                case "3": menuCalculerPointDeCollecte(scanner); break;
                case "0": return;
                default: System.out.println("Choix inv.");
            }
        }
    }
    private void menuCalculerEncombrants(Scanner scanner) {
        while (true) {
            System.out.println("=== Choisir le calcul d'itineraire voulu ===");
            System.out.println("1. Postier Chinois");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
        }
    }
    private void menuCalculerPoubelle(Scanner scanner) {
        while (true) {
            System.out.println("=== Choisir le calcul d'itineraire voulu ===");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
        }
    }
    private void menuCalculerPointDeCollecte(Scanner scanner) {
        while (true) {
            System.out.println("=== Choisir le calcul d'itineraire voulu ===");
            System.out.println("1. TSP (Plus Proche Voisin)");
            System.out.println("2. TSP (MST)");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    if(graphePDC == null){
                        System.out.println("Le graphe de point de collecte est vide");
                    }else{
                        Tournee t = RechercheItinerairePointDeCollecte.PlusProcheVoisinTSP(graphePDC);
                        System.out.println(t.getPointsVisites());
                        System.out.println("Distance : " + t.getDistanceTotale());
                    }
                    break;
                case "2":
                    if(graphePDC == null){
                        System.out.println("Le graphe de point de collecte est vide");
                    }else{
                        Tournee t = RechercheItinerairePointDeCollecte.MSTTSP(grapheRoutier);
                        System.out.println(t.getPointsVisites());
                        System.out.println("Distance : " + t.getDistanceTotale());
                    }
                    break;
                case "0": return;
                default: System.out.println("Choix inv.");
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
