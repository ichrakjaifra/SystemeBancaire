package models;

import utilitaire.ValidationUtils;

public class Retrait extends Operation {
    private String destination;

    public Retrait(String numero, double montant, String destination) {
        super(numero, montant);
        ValidationUtils.validerDestination(destination);
        this.destination = destination;
    }

    @Override
    public String getType() {
        return "RETRAIT";
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        ValidationUtils.validerDestination(destination);
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Retrait [identifiant=" + getNumero() + 
               ", date=" + getDate() + 
               ", montant=" + getMontant() + 
               ", destination=" + destination + "]";
    }
}