package LOTR;

public class Arme extends Objet {
    private int puissance;

    public Arme(String nom, int prix, int puissance) {
        super(nom, prix);
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }
}
