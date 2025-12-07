package Commun;

import CreneauxSecteurs.AlgorithmeColorationSecteurs;
import CreneauxSecteurs.GrapheSecteurs;
import CreneauxSecteurs.Secteur;
import ramassageHabitations.DemandeEncombrant;
import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Graphe.Route;
import ramassageHabitations.ItineraireRamassage;
import ramassageHabitations.TourneeRamassage;
import ramassagePointDeCollecte.Graphe.GraphePointDeCollecte;
import ramassageHabitations.RechercheItineraireRamassage;
import ramassagePointDeCollecte.RechercheItinerairePointDeCollecte;
import ramassagePointDeCollecte.TourneePointDeCollecte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static ramassageHabitations.RechercheItineraireRamassage.gererCollectePoubelles;

public class Entreprise extends Utilisateur {
    //Graphes
    private GrapheRoutier grapheRoutier = null;
    private GraphePointDeCollecte graphePDC = null;
    private GrapheSecteurs grapheSecteurs = null;
    //Tournées
    private List<TourneeRamassage> tourneesRamassages = new LinkedList<>();
    private List<TourneePointDeCollecte> tourneesPointDeCollectes = new LinkedList<>();
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
    public void menuEntreprise(Scanner scanner, Commune commune) {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("           MENU ENTREPRISE");
            System.out.println("==============================================");
            System.out.println("1. Calculer un itineraire de ramassage");
            System.out.println("2. Planifier jours collectes");
            System.out.println("3. Programmer camions");
            System.out.println("4. Attribuer tournées à secteur");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1": menuCalculerItineraire(scanner, commune); break;
                case "2":
                    try {
                        AlgorithmeColorationSecteurs.Colorisation(grapheSecteurs);
                    }catch(NullPointerException e) {
                        e.getMessage();
                    }break;
                case "3":
                    if(camion_simultane == camions.size()){
                        System.out.println("Impossible de programmer un camion, nombre max simultané atteint");
                        break;
                    }
                    programmer_camions(scanner); break;
                case "4": programmer_secteur(scanner); break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }

    //Choix du calcul d'itinéraire
    private void menuCalculerItineraire(Scanner scanner, Commune commune){
        while (true) {
            System.out.println("\n=== Choisir le type d'itineraire de ramassage ===");
            System.out.println("1. Ramassage encombrants");
            System.out.println("2. Ramassage poubelles (Postier Chinois)");
            System.out.println("3. Ramassage point de collecte");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": menuCalculerEncombrants(scanner, commune);break;
                case "2": tourneesRamassages.add(gererCollectePoubelles(grapheRoutier)); break;
                case "3": menuCalculerPointDeCollecte(scanner);break;
                case "0": return;
                default: System.out.println("Choix inv.");
            }
        }
    }
    private void menuCalculerEncombrants(Scanner scanner, Commune commune) {
        while (true) {
            System.out.println("\n=== Choisir le calcul d'itineraire voulu ===");
            System.out.println("1. Itineraire pour un seul ramassage");
            System.out.println("2. Itineraire pour plusieurs ramassages");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    DemandeEncombrant d = commune.menuEncombrant(scanner, false).getFirst();
                    ItineraireRamassage ir = RechercheItineraireRamassage.itineraire_encombrant(grapheRoutier, d.getRue());
                    if(ir == null){
                        System.out.println("Itineraire non trouvé");
                        return;
                    }
                    tourneesRamassages.add(new TourneeRamassage("Dijsktra", ir, d));
                    break;
                case "2":
                    LinkedList<DemandeEncombrant> ds = commune.menuEncombrant(scanner, true);
                    LinkedList<Route> routes = new LinkedList<>();
                    for(DemandeEncombrant de : ds){
                        routes.add(de.getRue());
                    }
                    ItineraireRamassage ir2 = RechercheItineraireRamassage.itineraire_encombrants(grapheRoutier, routes);
                    if(ir2 == null){
                        System.out.println("Itineraire non trouvé");
                        return;
                    }
                    tourneesRamassages.add(new TourneeRamassage("TSP + Dijsktra", ir2, ds));
                case "0": return;
                default: System.out.println("Choix inv.");
            }
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
                    case "1":
                        TourneePointDeCollecte t1 = RechercheItinerairePointDeCollecte.PlusProcheVoisinTSP(graphePDC);
                        tourneesPointDeCollectes.add(t1);
                        System.out.println(t1.getItineraire());
                        System.out.println("Distance : " + t1.getItineraire().getDistance());
                        break;
                    case "2":
                        TourneePointDeCollecte t2 = RechercheItinerairePointDeCollecte.MSTTSP(graphePDC);
                        System.out.println(t2.getItineraire());
                        System.out.println("Distance : " + t2.getItineraire().getDistance());
                        break;
                    case "0": return;
                    default: System.out.println("Choix inv.");
                }
            }
        }
    }

    //Menu programmation camions
    public void programmer_camions(Scanner scanner){
        while (true) {
            System.out.println("\n=== Programmation camion ===");
            System.out.println("1. Attribuer camion à tournée ramassage");
            System.out.println("2. Attribuer camion à tournée point de collecte");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    if(tourneesRamassages == null || tourneesRamassages.isEmpty()) {
                        System.out.println("Tournée non initilialisée");
                    }else{
                        choisir_tournee(scanner, new LinkedList<>(tourneesRamassages));
                    }
                    break;
                case "2":
                    if(tourneesPointDeCollectes == null || tourneesPointDeCollectes.isEmpty()){
                        System.out.println("Tournée non initilialisée");
                    }
                    else{
                        choisir_tournee(scanner, new LinkedList<>(tourneesPointDeCollectes));
                    }
                    break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }
    private void choisir_tournee(Scanner scanner, LinkedList<Tournee> liste) {
        while (true) {
            System.out.println("\n=== Choisissez Tournée ===");

            //affichage tournées
            for (int i = 0; i < liste.size(); i++) {
                System.out.println((i + 1) + ". " + liste.get(i));
            }
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            int choix = Integer.parseInt(scanner.nextLine());

            if (choix == 0) return;
            if (choix >= 1 && choix <= liste.size()) {
                Tournee t = liste.get(choix - 1);
                Camion c = choisir_Camions(scanner);
                if (c != null) {
                    t.setCamion(c);
                    System.out.println("Camion attribué à la tournée");
                    camion_simultane++;
                }
                return;
            } else {
                System.out.println("Choix invalide");
            }
        }
    }
    private Camion choisir_Camions(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Choisissez Camion ===");
            LinkedList<Camion> liste = new LinkedList<>(camions);
            //Affichage camions
            for (int i = 0; i < liste.size(); i++) {
                System.out.println((i + 1) + ". " + liste.get(i));
            }
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            int choix = Integer.parseInt(scanner.nextLine());

            //Actions
            if (choix == 0) return null;
            if (choix >= 1 && choix <= liste.size()) {
                return liste.get(choix - 1);
            }
            System.out.println("Choix invalide");
        }
    }

    //Menu programation Secteur
    private void programmer_secteur(Scanner scanner){
        while (true) {
            System.out.println("\n=== Choisissez Secteur ===");
            LinkedList<Secteur> liste = new LinkedList<>(grapheSecteurs.getSecteurs());
            //Affichage camions
            for (int i = 0; i < liste.size(); i++) {
                System.out.println((i + 1) + ". " + liste.get(i));
            }
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            int choix = Integer.parseInt(scanner.nextLine());

            //Actions
            if (choix == 0) return;
            if (choix >= 1 && choix <= liste.size()) {
                Secteur s = liste.get(choix - 1);
                s.setTournee(renvoyer_Tournee(scanner, s));
            }
            System.out.println("Choix invalide");
        }
    }
    private Tournee renvoyer_Tournee(Scanner scanner, Secteur secteur) {
        while (true) {
            System.out.println("\n=== Choisissez Tournée ===");
            LinkedList<Tournee> liste = new LinkedList<>(tourneesRamassages);
            liste.addAll(tourneesRamassages);

            //Affichage Tournee
            for (int i = 0; i < liste.size(); i++) {
                System.out.println((i + 1) + ". " + liste.get(i));
            }
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            int choix = Integer.parseInt(scanner.nextLine());

            //Actions
            if (choix == 0) return null;
            if (choix >= 1 && choix <= liste.size()) {
                Tournee t = liste.get(choix - 1);
                if(t.getCamion() == null){
                    System.out.println("Erreur, camion non initialisé");
                    return null;
                }
                if(secteur.getQuantiteEstimee() > t.getCamion().getCapaciteMax()) {
                    System.out.println("Erreur, taille du camion trop faible");
                    return null;
                }
                return t;
            }
            System.out.println("Choix invalide");
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
    public void setTourneesRamassages(LinkedList<TourneeRamassage> tourneesRamassages) { this.tourneesRamassages = tourneesRamassages;}
    public void setTourneesPointDeCollecte(LinkedList<TourneePointDeCollecte> TPDC) { this.tourneesPointDeCollectes = TPDC;}
}
