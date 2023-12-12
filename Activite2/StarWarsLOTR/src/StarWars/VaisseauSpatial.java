package StarWars;

public abstract class VaisseauSpatial extends Arme implements Cible {
    protected int etat;
    protected int blindage;

    public VaisseauSpatial(int puissance, int etat, int blindage) {
        super(puissance);
        this.etat = etat;
        this.blindage = blindage;
    }

    @Override
    public void toucher(int puissance) {
        int impact = puissance / blindage;
        etat -= impact;
        if (etat < 0) {
            etat = 0;
        }
    }
}
