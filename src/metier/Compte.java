package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> operations;

    public Compte(String code, double solde) {
        this.code = code;
        this.solde = solde;
        this.operations = new ArrayList<>();
    }

    public abstract boolean retirer(double montant);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public void deposer(double montant, String source) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        this.solde += montant;
        String numeroOperation = "DEP-" + UUID.randomUUID().toString().substring(0, 8);
        operations.add(new Versement(numeroOperation, montant, source));
    }

    // Getters
    public String getCode() { return code; }
    public double getSolde() { return solde; }
    public List<Operation> getOperations() { return operations; }
}
