package SWLOTR;

public abstract class Personnage implements Cible {
    protected String nom;
    protected int pointsVie, x, y, v;

    public Personnage(String nom, int x, int y, int v) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.pointsVie = 100;
        this.v = v;
    }

    public int getPointsVie() {
        return this.pointsVie;
    }

    public void setPointsVie(int pv) {
        this.pointsVie = pv;
    }

    public void seDeplacer(int dx, int dy, int t) {
        final double sqrt = Math.sqrt(dx * dx + dy * dy);
        this.x = (int) (this.x + dx * this.v * t / sqrt);
        this.y = (int) (this.y + dy * this.v * t / sqrt);
    }
}
