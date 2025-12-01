package Commun;

import CreneauxSecteurs.Secteur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Commune extends Utilisateur {
    //Fonction pour charger un graphe
    public void chargerGraphe(Scanner scanner, Entreprise entreprise) {
        String choix;
        while(true){
            System.out.println("=== Selectionnez le type de graphe souhaite ===");
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

        File fichier;
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionnez un fichier de graphe (.txt)");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers texte (*.txt)", "txt"));
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                fichier = fileChooser.getSelectedFile();
                System.out.println("Graphe chargé avec succès.");
            }else {
                System.out.println("Sélection annulée.");
                return;
            }

        }catch (HeadlessException e){
            System.out.println("Mode graphique indisponible.");
            System.out.print("Chemin du fichier : ");
            String chemin = scanner.nextLine();

            fichier = new File(chemin);
            if (!fichier.exists()) {
                System.out.println("Le fichier n'existe pas.");
                return;
            } else {
                System.out.println("Graphe chargé avec succès.");
            }
        }
        try {
            switch (choix) {
                case "1":
                    entreprise.setGrapheRoutier(ChargeurGraphe.chargerGrapheRoutier(fichier));
                    break;
                case "2":
                    //entreprise.setGraphePointDeCollecte(ChargeurGraphe.chargerGraphePDC(fichier));
                    break;
                case "3":
                    //entreprise.setGrapheSecteurs(ChargeurGraphe.chargerGrapheSecteurs(fichier));
                    break;
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture dans le fichier.");
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

    public void menuCollectivite(Scanner scanner, Entreprise entreprise) {
        while (true) {
            System.out.println("=== Menu Collectivité ===");
            System.out.println("1. Soumettre plan du territoire a couvrir");
            //Afficher les sommets
            System.out.println("2. Afficher les secteurs");
            System.out.println("3. Afficher couleurs des secteurs");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": chargerGraphe(scanner, entreprise); break;
                case "2": break;
                case "3":
                    if(entreprise.getGrapheSecteurs() != null){
                        for (Secteur s : entreprise.getGrapheSecteurs().getSecteurs())
                            System.out.println(s.getNom() + " → Jour " + s.getCouleur());
                    }
                    break;
                case "0": return;
                default: System.out.println("Choix inv");
            }
        }
    }
}
