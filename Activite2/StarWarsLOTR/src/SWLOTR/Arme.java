package SWLOTR;

public class Arme extends Objet {
    private int puissance;

    public Arme(int puissance) {
        super("Arme", 0);
        this.puissance = puissance;
    }

    public Arme(String nom, int prix, int puissance) {
        super(nom, prix);
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }

    public void tirer(Cible cible) {
        cible.toucher(puissance);
    }
}
