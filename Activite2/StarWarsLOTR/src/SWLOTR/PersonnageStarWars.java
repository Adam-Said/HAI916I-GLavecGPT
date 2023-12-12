package SWLOTR;

public abstract class PersonnageStarWars extends Personnage {
    protected int precision; // precision de 0 à 100

    public PersonnageStarWars(String nom, int x, int y, int v, int precision) {
        super(nom, x, y, v);
        this.precision = precision;
    }

    public int getPrecision() {
        return this.precision;
    }
    @Override
    public void toucher(int puissance) {
        this.pointsVie -= puissance;
        if (this.pointsVie < 0) {
            this.pointsVie = 0;
        }
    }
}
