class Noeud {
    int valeur;
    Noeud gauche;
    Noeud droite;
    int hauteur;

    public Noeud(int valeur) {
        this.valeur = valeur;
        this.gauche = null;
        this.droite = null;
        this.hauteur = 1;
    }
}

public class AVLTree {
    // Fonction pour calculer la hauteur d'un noeud
    private int hauteur(Noeud N) {
        if (N == null)
            return 0;
        return N.hauteur;
    }

    // Fonction pour calculer le maximum de deux entiers
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Fonction pour effectuer une rotation à droite simple
    // Fonction pour effectuer une rotation à droite simple
    private Noeud faireSimpleRotationADroite(Noeud y) {
        Noeud x = y.gauche;
        if (x != null) {
            Noeud T2 = x.droite;

            // Effectuer la rotation
            x.droite = y;
            y.gauche = T2;

            // Mettre à jour les hauteurs
            y.hauteur = max(hauteur(y.gauche), hauteur(y.droite)) + 1;
            x.hauteur = max(hauteur(x.gauche), hauteur(x.droite)) + 1;

            return x;
        }
        return y;
    }

    // Fonction pour effectuer une rotation à gauche simple
    private Noeud faireSimpleRotationAGauche(Noeud x) {
        Noeud y = x.droite;
        if (y != null) {
            Noeud T2 = y.gauche;

            // Effectuer la rotation
            y.gauche = x;
            x.droite = T2;

            // Mettre à jour les hauteurs
            x.hauteur = max(hauteur(x.gauche), hauteur(x.droite)) + 1;
            y.hauteur = max(hauteur(y.gauche), hauteur(y.droite)) + 1;

            return y;
        }
        return x;
    }

    // Fonction pour effectuer une rotation à droite double
    private Noeud faireDoubleRotationADroite(Noeud z) {
        z.gauche = faireSimpleRotationAGauche(z.gauche);
        return faireSimpleRotationADroite(z);
    }

    // Fonction pour effectuer une rotation à gauche double
    private Noeud faireDoubleRotationAGauche(Noeud z) {
        z.droite = faireSimpleRotationADroite(z.droite);
        return faireSimpleRotationAGauche(z);
    }

    // Fonction pour équilibrer un arbre AVL après une insertion
    private Noeud equilibrerArbre(Noeud racine, int valeur) {
        int equilibre = hauteur(racine.gauche) - hauteur(racine.droite);

        // Rotation à droite simple
        if (equilibre > 1 && valeur < racine.gauche.valeur)
            return faireSimpleRotationADroite(racine);

        // Rotation à gauche simple
        if (equilibre < -1 && valeur > racine.droite.valeur)
            return faireSimpleRotationAGauche(racine);

        // Rotation à droite double
        if (equilibre > 1 && valeur > racine.gauche.valeur) {
            racine.gauche = faireDoubleRotationAGauche(racine.gauche);
            return faireSimpleRotationADroite(racine);
        }

        // Rotation à gauche double
        if (equilibre < -1 && valeur < racine.droite.valeur) {
            racine.droite = faireDoubleRotationADroite(racine.droite);
            return faireSimpleRotationAGauche(racine);
        }

        return racine;
    }

    // Fonction pour insérer une valeur dans l'arbre AVL
    private Noeud inserer(Noeud racine, int valeur) {
        // Insérer normalement comme dans un arbre binaire de recherche
        if (racine == null)
            return new Noeud(valeur);

        if (valeur < racine.valeur)
            racine.gauche = inserer(racine.gauche, valeur);
        else if (valeur > racine.valeur)
            racine.droite = inserer(racine.droite, valeur);
        else // Les valeurs égales ne sont pas autorisées
            return racine;

        // Mettre à jour la hauteur du noeud actuel
        racine.hauteur = 1 + max(hauteur(racine.gauche), hauteur(racine.droite));

        // Équilibrer l'arbre
        return equilibrerArbre(racine, valeur);
    }

    // Fonction pour trouver le successeur d'un noeud
    private Noeud trouverSuccesseur(Noeud racine) {
        Noeud courant = racine;
        while (courant.gauche != null)
            courant = courant.gauche;
        return courant;
    }

    // Fonction pour supprimer un noeud de l'arbre AVL
    private Noeud supprimer(Noeud racine, int valeur) {
        // Étape de suppression normale comme dans un arbre binaire de recherche
        if (racine == null)
            return racine;

        if (valeur < racine.valeur)
            racine.gauche = supprimer(racine.gauche, valeur);
        else if (valeur > racine.valeur)
            racine.droite = supprimer(racine.droite, valeur);
        else {
            // Noeud avec un seul enfant ou sans enfant
            if (racine.gauche == null || racine.droite == null) {
                Noeud temp = (racine.gauche != null) ? racine.gauche : racine.droite;

                // Aucun enfant
                if (temp == null) {
                    temp = racine;
                    racine = null;
                } else // Un enfant
                    racine = temp; // Copier le contenu du noeud non vide
            } else {
                // Noeud avec deux enfants
                Noeud temp = trouverSuccesseur(racine.droite);
                racine.valeur = temp.valeur;
                racine.droite = supprimer(racine.droite, temp.valeur);
            }
        }

        // Si l'arbre avait un seul noeud, le retourner
        if (racine == null)
            return racine;

        // Mettre à jour la hauteur du noeud actuel
        racine.hauteur = 1 + max(hauteur(racine.gauche), hauteur(racine.droite));

        // Équilibrer l'arbre
        return equilibrerArbre(racine, valeur);
    }

    // Fonction pour afficher l'arbre AVL de manière récursive
    private void afficherArbre(Noeud racine, int espace) {
        if (racine == null)
            return;

        espace += 10;

        afficherArbre(racine.droite, espace);

        System.out.println();
        for (int i = 10; i < espace; i++)
            System.out.print(" ");
        System.out.println(racine.valeur);

        afficherArbre(racine.gauche, espace);
    }

    // Fonction pour libérer la mémoire utilisée par l'arbre AVL
    private void libererMemoire(Noeud racine) {
        if (racine == null)
            return;

        libererMemoire(racine.gauche);
        libererMemoire(racine.droite);
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        Noeud racine = null;

        // Insérer des valeurs dans l'arbre AVL
        racine = avlTree.inserer(racine, 10);
        racine = avlTree.inserer(racine, 20);
        racine = avlTree.inserer(racine, 30);
        racine = avlTree.inserer(racine, 15);
        racine = avlTree.inserer(racine, 5);

        // Afficher l'arbre AVL
        System.out.println("Arbre AVL après insertion :");
        avlTree.afficherArbre(racine, 0);

        // Supprimer une valeur de l'arbre AVL
        racine = avlTree.supprimer(racine, 20);

        // Afficher l'arbre AVL après suppression
        System.out.println("\nArbre AVL après suppression de la valeur 20 :");
        avlTree.afficherArbre(racine, 0);

        // Libérer la mémoire utilisée par l'arbre AVL (en Java, la gestion de la mémoire est automatique)
        // Pas besoin d'appeler une fonction spécifique pour libérer la mémoire
    }
}
