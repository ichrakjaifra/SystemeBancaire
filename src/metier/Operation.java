package metier;

import java.time.LocalDateTime;

public abstract class Operation {
    protected String numero;
    protected LocalDateTime date;
    protected double montant;

    public Operation(String numero, double montant) {
        this.numero = numero;
        this.montant = montant;
        this.date = LocalDateTime.now();
    }

    // Getters
    public String getNumero() { return numero; }
    public LocalDateTime getDate() { return date; }
    public double getMontant() { return montant; }
    
    public abstract String getType();
}
