package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import utilitaire.ValidationUtils;

public abstract class Compte {
    private String code;
    private double solde;
    private List<Operation> operations;

    public Compte(String code, double solde) {
        ValidationUtils.validerCodeCompte(code);
        if (solde < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
        }
        this.code = code;
        this.solde = solde;
        this.operations = new ArrayList<>();
    }

    public abstract boolean retirer(double montant);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public void deposer(double montant, String source) {
        ValidationUtils.validerMontant(montant);
        ValidationUtils.validerSource(source);
        
        this.solde += montant;
        String numeroOperation = "DEP-" + UUID.randomUUID().toString().substring(0, 8);
        operations.add(new Versement(numeroOperation, montant, source));
    }

    // Getters
    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public List<Operation> getOperations() {
        return new ArrayList<>(operations); // Retourne une copie pour éviter la modification externe
    }

    // Setters protégés pour les classes filles
    protected void setSolde(double solde) {
        this.solde = solde;
    }

    protected void setCode(String code) {
        ValidationUtils.validerCodeCompte(code);
        this.code = code;
    }

    protected void ajouterOperation(Operation operation) {
        this.operations.add(operation);
    }
}