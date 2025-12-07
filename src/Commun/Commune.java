package Commun;

import CreneauxSecteurs.Secteur;
import ramassageHabitations.DemandeEncombrant;
import ramassageHabitations.Graphe.GrapheRoutier;
import ramassageHabitations.Graphe.Route;
import ramassageHabitations.ItineraireRamassage;
import ramassageHabitations.RechercheItineraireRamassage;
import ramassageHabitations.TourneeRamassage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import static Commun.ChargeurFichier.chargerFichier;

public class Commune extends Utilisateur {
    private LinkedList<DemandeEncombrant> listeDemandeEncombrant = new LinkedList<>();

    //Fonction pour charger un graphe
    public void chargerGraphe(Scanner scanner, Entreprise entreprise) {
        //Renvoie le choix
        String choix;
        while(true){
            System.out.println("\n=== Selectionnez le type de graphe souhaite ===");
            System.out.println("1. Graphe routier (ramassage habitations)");
            System.out.println("2. Graphe points de collecte");
            System.out.println("3. Graphe secteurs");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextLine();
            if(choix.equals("0")){ return;}
            if(choix.equals("1") || choix.equals("2") || choix.equals("3")){ break;}
            System.out.println("Choix invalide.");
        }

        //Chargement du fichier
        File fichier = chargerFichier(scanner, "graphe");
        if(fichier == null){
            return;
        }

        //Chargement des graphes
        try {
            switch (choix) {
                case "1":
                    entreprise.setGrapheRoutier(ChargeurFichier.chargerGrapheRoutier(fichier));
                    entreprise.setTourneesRamassages(new LinkedList<>());
                    System.out.println("Succès ! Le graphe contient " + entreprise.getGrapheRoutier().getIntersections().size() + " intersections.");
                    break;
                case "2":
                    entreprise.setGraphePointDeCollecte(ChargeurFichier.chargerGraphePDC(fichier));
                    System.out.println("Succès ! Le graphe contient " + entreprise.getGraphePointDeCollecte().getPointDeCollecte().size() + " points de collecte.");
                    entreprise.setTourneesPointDeCollecte(new LinkedList<>());
                    break;
                case "3":
                    entreprise.setGrapheSecteurs(ChargeurFichier.chargerGrapheSecteurs(fichier));
                    System.out.println("Succès ! Le graphe contient " + entreprise.getGrapheSecteurs().getSecteurs().size() + " secteurs.");
                    break;
            }

            System.out.println("Graphe chargé avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur de lecture dans le fichier.");
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

    //Fonction qui s'occupe des différentes fonctions de la collectivité
    public void menuCollectivite(Scanner scanner, Entreprise entreprise) {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("           MENU COLLECTIVITE");
            System.out.println("==============================================");
            System.out.println("1. Soumettre plan du territoire a couvrir");
            System.out.println("2. Regrouper demandes de collecte d'encombrant");
            //Afficher les sommets
            System.out.println("3. Afficher les secteurs");
            System.out.println("3. Afficher couleurs des secteurs");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": chargerGraphe(scanner, entreprise); break;
                case "2":
                    listeDemandeEncombrant = new LinkedList<>();
                    try {
                        Scanner sc = new Scanner(chargerFichier(scanner, "demande d'encombrant"));
                        while(sc.hasNext()){
                            String nom_rue = sc.nextLine().split(" : ")[1];
                            Route rue = entreprise.getGrapheRoutier().getRoute().get(nom_rue);
                            if(rue == null){
                                System.out.println("Erreur de lecture dans le fichier avec la rue : " + nom_rue);
                            }else{
                                listeDemandeEncombrant.add(new DemandeEncombrant(rue));
                            }
                        }
                        System.out.println("Demande d'encombrants chargé avec succès");
                        sc.close();
                    }catch (FileNotFoundException e){
                        System.out.println("Erreur de lecture dans le fichier.");
                    }
                    break;
                case "3":
                    if(entreprise.getGrapheSecteurs() != null){
                        for (Secteur s : entreprise.getGrapheSecteurs().getSecteurs())
                            System.out.println(s.getNom() + " → Jour " + s.getJour());
                    }
                    break;
                case "0": return;
                default: System.out.println("Choix inv");
            }
        }
    }

    //Fonction qui renvoie une ou plusieurs demande d'encombrant selon le choix de l'utilisateur
    public LinkedList<DemandeEncombrant> menuEncombrant(Scanner scanner, boolean plusieurs_demandes) {
        LinkedList<DemandeEncombrant> demandeEncombrants = new LinkedList<>();
        int choix_nombre = 1;
        while (true) {
            //Menu pour avoir le nombre de demande
            while(plusieurs_demandes) {
                System.out.println("\nChoissisez le nombre demande d'encombrant souhaité (10 max) (0. Retour) ");
                choix_nombre = Integer.parseInt(scanner.nextLine());
                if (choix_nombre == 0) {
                    return null;
                }
                if (choix_nombre >= 1 && choix_nombre <= 10) {
                    break;
                }
                System.out.println("Choix invalide");
            }

            //Choix de la demande d'encombrement
            System.out.println("\nChoissisez la rue de la demande d'encombrant souhaité (0. Retour) ");
            for(int i = 0; i < choix_nombre; i++){
                DemandeEncombrant d = null;
                System.out.print("Votre choix : ");
                String choix_encombrant = scanner.nextLine();
                if(choix_encombrant.equals("0")) {
                    return null;
                }
                for(DemandeEncombrant demandeEncombrant : listeDemandeEncombrant){
                    if(demandeEncombrant.getRue().getNom().equals(choix_encombrant)){
                        d = demandeEncombrant;
                        listeDemandeEncombrant.remove(d);
                        demandeEncombrants.add(d);
                        break;
                    }
                }
                if(d == null){
                    System.out.println("Choix invalide, rue inexistante");
                    i--;
                }
            }
            return demandeEncombrants;
        }
    }
    //Getters
    public LinkedList<DemandeEncombrant> getListeDemandeEncombrant() { return listeDemandeEncombrant; }
}
