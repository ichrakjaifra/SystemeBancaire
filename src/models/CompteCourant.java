package models;

import java.util.UUID;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(String code, double solde, double decouvert) {
        super(code, solde);
        this.decouvert = decouvert;
    }

    @Override
    public boolean retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        if (solde - montant >= -decouvert) {
            solde -= montant;
            String numeroOperation = "RET-" + UUID.randomUUID().toString().substring(0, 8);
            operations.add(new Retrait(numeroOperation, montant, "Distributeur ATM"));
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        return 0; // Pas d’intérêts sur compte courant
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Courant - Code: " + code +
                         ", Solde: " + solde +
                         ", Découvert autorisé: " + decouvert);
    }

    public double getDecouvert() {
        return decouvert;
    }
}
