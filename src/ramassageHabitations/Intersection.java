package ramassageHabitations;

public class Intersection {
    private int id;
    private String nom;
    private double x;
    private double y;

    public Intersection(int id, String nom, double x, double y) {
        this.id = id;
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
