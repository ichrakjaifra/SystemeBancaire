package models;

import java.time.LocalDateTime;

public abstract class Operation {
    private String numero;
    private LocalDateTime date;
    private double montant;

    public Operation(String numero, double montant) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Le numéro d'opération ne peut pas être vide");
        }
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        
        this.numero = numero;
        this.montant = montant;
        this.date = LocalDateTime.now();
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    // Setters
    protected void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Le numéro d'opération ne peut pas être vide");
        }
        this.numero = numero;
    }

    protected void setMontant(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        this.montant = montant;
    }

    protected void setDate(LocalDateTime date) {
        this.date = date;
    }

    public abstract String getType();
}