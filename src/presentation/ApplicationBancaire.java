package presentation;

import service.ServiceBancaire;
import metier.Operation;
import java.util.List;
import java.util.Scanner;

public class ApplicationBancaire {
    private ServiceBancaire serviceBancaire;
    private Scanner scanner;

    public ApplicationBancaire() {
        this.serviceBancaire = new ServiceBancaire();
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        boolean quitter = false;
        
        while (!quitter) {
            afficherMenu();
            int choix = getEntier("Choisissez une option: ");
            
            switch (choix) {
                case 1:
                    creerCompte();
                    break;
                case 2:
                    effectuerVersement();
                    break;
                case 3:
                    effectuerRetrait();
                    break;
                case 4:
                    effectuerVirement();
                    break;
                case 5:
                    consulterSolde();
                    break;
                case 6:
                    consulterOperations();
                    break;
                case 7:
                    voirTousComptes();
                    break;
                case 8:
                    quitter = true;
                    System.out.println("Merci d'avoir utilisé notre système bancaire.");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        
        scanner.close();
    }

    private void afficherMenu() {
        System.out.println("\n=== SYSTÈME DE GESTION BANCAIRE ===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Effectuer un versement");
        System.out.println("3. Effectuer un retrait");
        System.out.println("4. Effectuer un virement");
        System.out.println("5. Consulter le solde");
        System.out.println("6. Consulter les opérations");
        System.out.println("7. Voir tous les comptes");
        System.out.println("8. Quitter");
        System.out.println("===================================");
    }

    private void creerCompte() {
        System.out.println("\n--- Création de compte ---");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");
        int type = getEntier("Choisissez le type de compte: ");
        
        double soldeInitial = getDouble("Solde initial: ");
        
        if (type == 1) {
            double decouvert = getDouble("Découvert autorisé: ");
            serviceBancaire.creerCompteCourant(soldeInitial, decouvert);
        } else if (type == 2) {
            double tauxInteret = getDouble("Taux d'intérêt (%): ");
            serviceBancaire.creerCompteEpargne(soldeInitial, tauxInteret);
        } else {
            System.out.println("Type de compte invalide.");
        }
    }

    private void effectuerVersement() {
        System.out.println("\n--- Versement ---");
        String codeCompte = getString("Code du compte: ");
        double montant = getDouble("Montant: ");
        String source = getString("Source (ex: Virement, Espèces): ");
        
        try {
            serviceBancaire.deposer(codeCompte, montant, source);
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void effectuerRetrait() {
        System.out.println("\n--- Retrait ---");
        String codeCompte = getString("Code du compte: ");
        double montant = getDouble("Montant: ");
        
        try {
            serviceBancaire.retirer(codeCompte, montant);
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void effectuerVirement() {
        System.out.println("\n--- Virement ---");
        String compteSource = getString("Compte source: ");
        String compteDestination = getString("Compte destination: ");
        double montant = getDouble("Montant: ");
        
        try {
            serviceBancaire.transferer(compteSource, compteDestination, montant);
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void consulterSolde() {
        System.out.println("\n--- Consultation de solde ---");
        String codeCompte = getString("Code du compte: ");
        
        try {
            double solde = serviceBancaire.getSolde(codeCompte);
            System.out.println("Solde du compte " + codeCompte + ": " + solde);
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void consulterOperations() {
        System.out.println("\n--- Consultation des opérations ---");
        String codeCompte = getString("Code du compte: ");
        
        try {
            List<Operation> operations = serviceBancaire.getOperations(codeCompte);
            if (operations.isEmpty()) {
                System.out.println("Aucune opération pour ce compte.");
            } else {
                System.out.println("Opérations du compte " + codeCompte + ":");
                operations.forEach(op -> System.out.println(op));
            }
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void voirTousComptes() {
        System.out.println("\n--- Tous les comptes ---");
        serviceBancaire.getTousLesComptes().forEach(compte -> compte.afficherDetails());
    }

    private int getEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre valide.");
            scanner.next();
            System.out.print(message);
        }
        int valeur = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne
        return valeur;
    }

    private double getDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Veuillez entrer un nombre valide.");
            scanner.next();
            System.out.print(message);
        }
        double valeur = scanner.nextDouble();
        scanner.nextLine(); // Consommer le retour à la ligne
        return valeur;
    }

    private String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        ApplicationBancaire app = new ApplicationBancaire();
        app.demarrer();
    }
}
