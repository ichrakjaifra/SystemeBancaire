package models;

import java.util.UUID;
import utilitaire.ValidationUtils;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(String code, double solde, double decouvert) {
        super(code, solde);
        ValidationUtils.validerDecouvert(decouvert);
        this.decouvert = decouvert;
    }

    @Override
    public boolean retirer(double montant) {
        ValidationUtils.validerMontant(montant);
        
        if (getSolde() - montant >= -decouvert) {
            setSolde(getSolde() - montant);
            String numeroOperation = "RET-" + UUID.randomUUID().toString().substring(0, 8);
            ajouterOperation(new Retrait(numeroOperation, montant, "Distributeur ATM"));
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        return 0; // Pas d'intérêts sur compte courant
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Courant - Code: " + getCode() + 
                          ", Solde: " + getSolde() + 
                          ", Découvert autorisé: " + decouvert);
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        ValidationUtils.validerDecouvert(decouvert);
        this.decouvert = decouvert;
    }
}