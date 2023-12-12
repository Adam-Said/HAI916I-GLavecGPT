package SWLOTR;

public class Humanoide extends PersonnageLOTR {
    private Objet objet;

    public Humanoide(String nom, int x, int y, int v) {
        super(nom, x, y, v);
    }

    public Objet getObjet() {
        return objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    // Acquérir un objet
    public void acquérirObjet(Objet objet) {
        this.objet = objet;
    }

    // Se séparer de l'objet
    public void seSéparerObjet() {
        this.objet = null;
    }

    // Donner l'objet à un autre humanoïde
    public void donnerObjet(Humanoide destinataire) {
        if (objet != null) {
            destinataire.acquérirObjet(objet);
            seSéparerObjet();
        }
    }

    // Parler avec surcharge pour inclure l'information sur l'objet détenu
    @Override
    public String parler() {
        if (objet != null) {
            return " Je détiens un objet : " + objet.getNom();
        } else {
            return " Je n'ai pas d'objet.";
        }
    }

    public void utiliserArme(Arme arme, Personnage cible) {
        // Vérifier si l'arme appartient au possesseur
        if (objet instanceof Arme && objet == arme) {
            // Réduire les points de vie de la cible en fonction de la puissance de l'arme
            int nouveauxPointsVie = cible.getPointsVie() - arme.getPuissance();
            cible.setPointsVie(Math.max(0, nouveauxPointsVie));
        } else {
            System.out.println(this.nom + " ne peut pas utiliser cette arme.");
        }
    }

    @Override
    public void toucher(int puissance) {

    }
}