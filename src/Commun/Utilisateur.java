package Commun;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Utilisateur {
    private String login;

    //Getters
    public String getLogin() { return login;}

    //Fonction pour se connecter
    public boolean seConnecter(String fichier, Scanner sc) throws FileNotFoundException {
        //Lire Fichier
        Scanner read = new Scanner(new File(fichier));
        String id = read.nextLine().split(":")[1];
        String mdp = read.nextLine().split(":")[1];

        //Rentre identifiants
        System.out.println("Veuillez rentez les identifiants :");
        System.out.print("Login : ");
        login = sc.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = sc.nextLine();

        if (login.equals(id) && motDePasse.equals(mdp)) {
            System.out.println(login + " se connecte au système.");
            return true;
        }else{
            System.out.println("Erreur d'identifiant ou de mot de passe");
            return false;
        }
    }
}
