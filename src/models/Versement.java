package models;

import utilitaire.ValidationUtils;

public class Versement extends Operation {
    private String source;

    public Versement(String numero, double montant, String source) {
        super(numero, montant);
        ValidationUtils.validerSource(source);
        this.source = source;
    }

    @Override
    public String getType() {
        return "VERSEMENT";
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        ValidationUtils.validerSource(source);
        this.source = source;
    }

    @Override
    public String toString() {
        return "Versement [numero=" + getNumero() + 
               ", date=" + getDate() + 
               ", montant=" + getMontant() + 
               ", source=" + source + "]";
    }
}