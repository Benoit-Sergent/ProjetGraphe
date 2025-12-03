import Commun.Commune;
import Commun.Entreprise;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ApplicationConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Commune c = new Commune();
        Entreprise e = new Entreprise();

        try {
            e.CreationCamion("src/CamionsInfo.txt");
        }catch(FileNotFoundException f){
            System.out.println(f.getMessage());
            return;
        }

        while (true) {
            System.out.println("=== Système de collecte des déchets ===");
            System.out.println("1. Menu Collectivité");
            System.out.println("2. Menu Entreprise de collecte");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": try {
                    if(c.seConnecter("src/CleCommune.txt", scanner)) { c.menuCollectivite(scanner, e);}
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }break;
                case "2":
                    try {
                        if(e.seConnecter("src/CleEntreprise.txt", scanner)) { e.menuEntreprise(scanner);}
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }break;
                case "0":
                    System.out.println("Au revoir.");
                    scanner.close();
                    return;
                default: System.out.println("Choix invalide.");
            }
        }
    }
}
