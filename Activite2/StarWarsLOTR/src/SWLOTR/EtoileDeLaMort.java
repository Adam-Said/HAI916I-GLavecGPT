package SWLOTR;

public class EtoileDeLaMort extends VaisseauPilote {
    private static EtoileDeLaMort instance;

    private EtoileDeLaMort() {
        // Initialiser avec une puissance de 1000 et un blindage de 5
        super(1000, 100, 5, null);
    }

    public static EtoileDeLaMort getInstance() {
        if (instance == null) {
            instance = new EtoileDeLaMort();
        }
        return instance;
    }
}
