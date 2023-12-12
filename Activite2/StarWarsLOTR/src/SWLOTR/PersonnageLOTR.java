package SWLOTR;


public abstract class PersonnageLOTR extends Personnage {
    protected int connaissances;

    public PersonnageLOTR(String nom, int x, int y, int v) {
        super(nom, x, y, v);
        this.connaissances = 0;
    }

    public int getConnaissances() {
        return this.connaissances;
    }

    public void setConnaissances(int co) {
        this.connaissances = co;
    }

    public abstract String parler();
}
