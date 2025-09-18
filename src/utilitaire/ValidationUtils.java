package utilitaire;

import java.util.regex.Pattern;

public class ValidationUtils {
    
    private static final Pattern CODE_COMPTE_PATTERN = Pattern.compile("^CPT-\\d{5}$");
    
    public static void validerMontant(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
    }
    
    public static void validerCodeCompte(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code du compte ne peut pas être vide");
        }
        if (!CODE_COMPTE_PATTERN.matcher(code).matches()) {
            throw new IllegalArgumentException("Le code du compte doit respecter le format CPT-XXXXX");
        }
    }
    
    public static void validerTauxInteret(double taux) {
        if (taux < 0) {
            throw new IllegalArgumentException("Le taux d'intérêt ne peut pas être négatif");
        }
    }
    
    public static void validerDecouvert(double decouvert) {
        if (decouvert < 0) {
            throw new IllegalArgumentException("Le découvert ne peut pas être négatif");
        }
    }
    
    public static void validerSource(String source) {
        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("La source ne peut pas être vide");
        }
    }
    
    public static void validerDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("La destination ne peut pas être vide");
        }
    }
}
