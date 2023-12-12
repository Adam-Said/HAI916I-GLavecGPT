package StarWars;

import java.util.Random;

public class Drone extends VaisseauSpatial {
    private int precisionIntrinseque;

    public Drone(int puissance, int etat, int blindage, int precisionIntrinseque) {
        super(puissance, etat, blindage);
        this.precisionIntrinseque = precisionIntrinseque;
    }

    @Override
    public void tirer(Cible cible) {
        if (peutToucher()) {
            super.tirer(cible);
        }
    }

    private boolean peutToucher() {
        Random random = new Random();
        return random.nextInt(100) < precisionIntrinseque;
    }
}