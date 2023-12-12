package StarWars;

public class Arme {
    private int puissance;

    public Arme(int puissance) {
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }

    public void tirer(Cible cible) {
        cible.toucher(puissance);
    }
}