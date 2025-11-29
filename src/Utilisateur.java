public abstract class Utilisateur {
    private String login;
    private String motDePasse;

    public Utilisateur(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public String getLogin() {
        return login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void seConnecter() {
        System.out.println(login + " se connecte au système.");
    }
}
