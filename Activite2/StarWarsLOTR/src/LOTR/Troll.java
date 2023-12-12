package LOTR;

public class Troll extends Personnage implements Monstre {
    private int force;
    private int puanteur;

    public Troll(String nom, int x, int y, int v, int force, int puanteur) {
        super(nom, x, y, v);
        this.force = force;
        this.puanteur = puanteur;
    }

    public int getForce() {
        return force;
    }

    public int getPuanteur() {
        return puanteur;
    }

    // Attaque un personnage en lui faisant perdre des points de vie
    public void attaque(Personnage p) {
        // Vérifier que le troll n'attaque pas un autre troll
        if (!(p instanceof Troll)) {
            int degats = force + puanteur / 10;
            p.setPointsVie(p.getPointsVie() - degats);
            System.out.println("j'attaque " + p +
                    " et lui fait perdre " + degats + " points de vie.");
        } else {
            System.out.println("je ne peux pas attaquer un autre troll.");
        }
    }

    // Surcharge de la méthode parler pour afficher spécifiquement le dialogue du troll
    @Override
    public String parler() {
        return "Grrr... Je suis un troll.";
    }
}
