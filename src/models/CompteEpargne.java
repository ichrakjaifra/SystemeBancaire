package models;

import java.util.UUID;
import utilitaire.ValidationUtils;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(String code, double solde, double tauxInteret) {
        super(code, solde);
        ValidationUtils.validerTauxInteret(tauxInteret);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public boolean retirer(double montant) {
        ValidationUtils.validerMontant(montant);
        
        if (getSolde() >= montant) {
            setSolde(getSolde() - montant);
            String numeroOperation = "RET-" + UUID.randomUUID().toString().substring(0, 8);
            ajouterOperation(new Retrait(numeroOperation, montant, "Retrait épargne"));
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        return getSolde() * tauxInteret / 100;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Épargne - Code: " + getCode() + 
                          ", Solde: " + getSolde() + 
                          ", Taux d'intérêt: " + tauxInteret + "%");
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        ValidationUtils.validerTauxInteret(tauxInteret);
        this.tauxInteret = tauxInteret;
    }
}