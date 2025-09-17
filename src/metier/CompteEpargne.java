package metier;

import java.util.UUID;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(String code, double solde, double tauxInteret) {
        super(code, solde);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public boolean retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        if (solde >= montant) {
            solde -= montant;
            String numeroOperation = "RET-" + UUID.randomUUID().toString().substring(0, 8);
            operations.add(new Retrait(numeroOperation, montant, "Retrait épargne"));
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        return solde * tauxInteret / 100;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Épargne - Code: " + code +
                         ", Solde: " + solde +
                         ", Taux d'intérêt: " + tauxInteret + "%");
    }

    public double getTauxInteret() {
        return tauxInteret;
    }
}
