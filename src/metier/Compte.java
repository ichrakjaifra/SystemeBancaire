package metier;



public abstract class Compte {
    protected String code;
    protected double solde;
    

    public Compte(String code, double solde) {
        this.code = code;
        this.solde = solde;
        
    }

    public abstract boolean retirer(double montant);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    

    // Getters
    public String getCode() { return code; }
    public double getSolde() { return solde; }
    
}
