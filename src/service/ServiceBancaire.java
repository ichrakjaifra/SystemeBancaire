package service;

import java.util.*;
import java.util.stream.Collectors;

import models.*;

public class ServiceBancaire {
    private Map<String, Compte> comptes;

    public ServiceBancaire() {
        this.comptes = new HashMap<>();
    }

    public void creerCompteCourant(double soldeInitial, double decouvert) {
        String code = genererCodeCompte();
        Compte compte = new CompteCourant(code, soldeInitial, decouvert);
        comptes.put(code, compte);
        System.out.println("Compte courant créé avec succès. Code: " + code);
    }

    public void creerCompteEpargne(double soldeInitial, double tauxInteret) {
        String code = genererCodeCompte();
        Compte compte = new CompteEpargne(code, soldeInitial, tauxInteret);
        comptes.put(code, compte);
        System.out.println("Compte épargne créé avec succès. Code: " + code);
    }

    public void deposer(String codeCompte, double montant, String source) {
        Compte compte = comptes.get(codeCompte);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé");
        }
        compte.deposer(montant, source);
        System.out.println("Dépôt effectué avec succès.");
    }

    public boolean retirer(String codeCompte, double montant) {
        Compte compte = comptes.get(codeCompte);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé");
        }
        boolean succes = compte.retirer(montant);
        if (succes) {
            System.out.println("Retrait effectué avec succès.");
        } else {
            System.out.println("Fonds insuffisants pour effectuer le retrait.");
        }
        return succes;
    }

    public boolean transferer(String codeCompteSource, String codeCompteDestination, double montant) {
        Compte compteSource = comptes.get(codeCompteSource);
        Compte compteDestination = comptes.get(codeCompteDestination);
        
        if (compteSource == null || compteDestination == null) {
            throw new IllegalArgumentException("Un ou plusieurs comptes non trouvés");
        }
        
        if (compteSource.retirer(montant)) {
            compteDestination.deposer(montant, "Virement depuis " + codeCompteSource);
            System.out.println("Virement effectué avec succès.");
            return true;
        } else {
            System.out.println("Fonds insuffisants pour effectuer le virement.");
            return false;
        }
    }

    public double getSolde(String codeCompte) {
        Compte compte = comptes.get(codeCompte);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé");
        }
        return compte.getSolde();
    }

    public List<Operation> getOperations(String codeCompte) {
        Compte compte = comptes.get(codeCompte);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé");
        }
        return compte.getOperations();
    }

    public void afficherDetailsCompte(String codeCompte) {
        Compte compte = comptes.get(codeCompte);
        if (compte == null) {
            throw new IllegalArgumentException("Compte non trouvé");
        }
        compte.afficherDetails();
    }

    public List<Compte> getTousLesComptes() {
        return new ArrayList<>(comptes.values());
    }

    private String genererCodeCompte() {
        Random random = new Random();
        int nombre = random.nextInt(90000) + 10000; // Génère un nombre entre 10000 et 99999
        return "CPT-" + nombre;
    }
}
