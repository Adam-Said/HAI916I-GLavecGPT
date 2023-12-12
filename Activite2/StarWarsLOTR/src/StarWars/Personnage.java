package StarWars;

public abstract class Personnage implements Cible {
    protected String nom;
    protected int pointsVie, x, y, v, precision; // precision de 0 à 100

    public Personnage(String n, int x, int y, int v, int precision) {
        this.nom = n;
        this.x = x;
        this.y = y;
        this.pointsVie = 100;
        this.v = v;
        this.precision = precision;
    }

    public int getPointsVie() {
        return this.pointsVie;
    }

    public void setPointsVie(int pv) {
        this.pointsVie = pv;
    }

    public int getPrecision() {
        return this.precision;
    }

    public void seDeplacer(int dx, int dy, int t) {
        final double sqrt = Math.sqrt(dx * dx + dy * dy);
        this.x = (int) (this.x + dx * this.v * t / sqrt);
        this.y = (int) (this.y + dy * this.v * t / sqrt);
    }

    @Override
    public void toucher(int puissance) {
        this.pointsVie -= puissance;
        if (this.pointsVie < 0) {
            this.pointsVie = 0;
        }
    }
} 
