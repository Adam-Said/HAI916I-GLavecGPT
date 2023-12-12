package SWLOTR;

class Document extends Objet {
    private int quantiteConnaissances;
    private boolean lu;  // Indique si le document a déjà été lu

    public Document(String nom, int prix, int quantiteConnaissances) {
        super(nom, prix);
        this.quantiteConnaissances = quantiteConnaissances;
        this.lu = false;
    }

    public int getQuantiteConnaissances() {
        return quantiteConnaissances;
    }

    public boolean estLu() {
        return lu;
    }

    // Lire le document pour augmenter les connaissances du personnage
    public void lire(Humanoide possesseur) {
        // Vérifier si le document appartient au possesseur et s'il n'a pas été lu
        if (possesseur.getObjet() instanceof Document && possesseur.getObjet() == this && !lu) {
            // Augmenter les connaissances du possesseur
            int nouvellesConnaissances = possesseur.getConnaissances() + quantiteConnaissances;
            possesseur.setConnaissances(nouvellesConnaissances);
            System.out.println(possesseur + " lit " + getNom() +
                    " et augmente ses connaissances de " + quantiteConnaissances +
                    ". Ses connaissances sont maintenant de " + possesseur.getConnaissances());
            lu = true;  // Marquer le document comme lu
        } else {
            System.out.println(possesseur + " ne peut pas lire ce document.");
        }
    }
}
