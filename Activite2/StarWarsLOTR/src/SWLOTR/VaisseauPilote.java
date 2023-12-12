package SWLOTR;

import java.util.Random;

public class VaisseauPilote extends VaisseauSpatial {
    private PersonnageStarWars pilote;

    public VaisseauPilote(int puissance, int etat, int blindage, PersonnageStarWars pilote) {
        super(puissance, etat, blindage);
        this.pilote = pilote;
    }

    @Override
    public void tirer(Cible cible) {
        if (peutToucher()) {
            super.tirer(cible);
        }
    }

    private boolean peutToucher() {
        Random random = new Random();
        return random.nextInt(100) < pilote.getPrecision();
    }
}