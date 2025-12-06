package Commun;

import CreneauxSecteurs.GrapheSecteurs;
import ramassageHabitations.GrapheRoutier;
import ramassageHabitations.TourneeRamassage;
import ramassagePointDeCollecte.GraphePointDeCollecte;
import ramassagePointDeCollecte.ItinerairePointDeCollecte;
import ramassagePointDeCollecte.RechercheItinerairePointDeCollecte;
import ramassagePointDeCollecte.TourneePointDeCollecte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Entreprise extends Utilisateur {
    //Graphes
    private GrapheRoutier grapheRoutier = null;
    private GraphePointDeCollecte graphePDC = null;
    private GrapheSecteurs grapheSecteurs = null;
    //Tournées
    private HashMap<String, TourneeRamassage> tourneesRamassages = new HashMap<>();
    private HashMap<String, TourneePointDeCollecte> tourneesPointDeCollectes = new HashMap<>();
    //Camion
    private LinkedList<Camion> camions = new LinkedList<>();
    private int camion_simultane = 0;

    //Creation camions
    public void CreationCamion(String fichier) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fichier));
        while(scanner.hasNext()) {
            String chaine = scanner.nextLine();
            String chaineImmatriculation = chaine.split(" ; ")[0];
            String chaineCapacite = chaine.split(" ; ")[1];
            String immatriculation = chaineImmatriculation.split(" : ")[1];
            int capacite = Integer.parseInt(chaineCapacite.split(" : ")[1]);
            camions.add(new Camion(capacite, immatriculation));
        }
        scanner.close();
    }

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
            if(graphePDC == null){
                System.out.println("Le graphe de point de collecte est vide");
            }else {
                switch (choix) {
                    case "1": {
                        TourneePointDeCollecte t;

                        if (!tourneesPointDeCollectes.containsKey("TSP")) {
                            t = RechercheItinerairePointDeCollecte.PlusProcheVoisinTSP(graphePDC);
                            tourneesPointDeCollectes.put("TSP", t);
                        } else {
                            t = tourneesPointDeCollectes.get("TSP");
                        }

                        System.out.println("Itinéraire (TSP)");
                        System.out.println(t.getItineraire()); // toString() de ItinerairePointDeCollecte
                        System.out.println("Distance totale : " + t.getItineraire().getDistance() + " m");
                        break;
                    }
                    case "2":
                        if(!tourneesPointDeCollectes.containsKey("MST")) {
                            TourneePointDeCollecte t = RechercheItinerairePointDeCollecte.MSTTSP(graphePDC);
                            tourneesPointDeCollectes.put("MST", t);
                            System.out.println(t.getItineraire());
                            System.out.println("Distance : " + t.getItineraire().getDistance());
                        }
                        break;
                    case "0": return;
                    default: System.out.println("Choix inv.");
                }
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
    public void setTourneesRamassages(HashMap<String, TourneeRamassage> tourneesRamassages) { this.tourneesRamassages = tourneesRamassages;}
    public void setTourneesPointDeCollecte(HashMap<String, TourneePointDeCollecte> TPDC) { this.tourneesPointDeCollectes = TPDC;}
}
