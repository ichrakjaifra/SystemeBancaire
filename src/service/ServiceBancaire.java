package service;

import java.util.*;
import models.*;
import utilitaire.CodeGenerator;
import utilitaire.ValidationUtils;

public class ServiceBancaire {
    private Map<String, Compte> comptes;

    public ServiceBancaire() {
        this.comptes = new HashMap<>();
    }

    public void creerCompteCourant(double soldeInitial, double decouvert) {
        try {
            ValidationUtils.validerDecouvert(decouvert);
            if (soldeInitial < 0) {
                throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
            }
            
            String code = CodeGenerator.genererCodeCompte(comptes.keySet());
            Compte compte = new CompteCourant(code, soldeInitial, decouvert);
            comptes.put(code, compte);
            System.out.println("Compte courant créé avec succès. Code: " + code);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du compte: " + e.getMessage());
        }
    }

    public void creerCompteEpargne(double soldeInitial, double tauxInteret) {
        try {
            ValidationUtils.validerTauxInteret(tauxInteret);
            if (soldeInitial < 0) {
                throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
            }
            
            String code = CodeGenerator.genererCodeCompte(comptes.keySet());
            Compte compte = new CompteEpargne(code, soldeInitial, tauxInteret);
            comptes.put(code, compte);
            System.out.println("Compte épargne créé avec succès. Code: " + code);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du compte: " + e.getMessage());
        }
    }

    public void deposer(String codeCompte, double montant, String source) {
        try {
            ValidationUtils.validerCodeCompte(codeCompte);
            Compte compte = comptes.get(codeCompte);
            if (compte == null) {
                throw new IllegalArgumentException("Compte non trouvé");
            }
            compte.deposer(montant, source);
            System.out.println("Dépôt effectué avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors du dépôt: " + e.getMessage());
        }
    }

    public boolean retirer(String codeCompte, double montant) {
        try {
            ValidationUtils.validerCodeCompte(codeCompte);
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
        } catch (Exception e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
            return false;
        }
    }

    public boolean transferer(String codeCompteSource, String codeCompteDestination, double montant) {
        try {
            ValidationUtils.validerCodeCompte(codeCompteSource);
            ValidationUtils.validerCodeCompte(codeCompteDestination);
            ValidationUtils.validerMontant(montant);
            
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
        } catch (Exception e) {
            System.out.println("Erreur lors du virement: " + e.getMessage());
            return false;
        }
    }

    public double getSolde(String codeCompte) {
        try {
            ValidationUtils.validerCodeCompte(codeCompte);
            Compte compte = comptes.get(codeCompte);
            if (compte == null) {
                throw new IllegalArgumentException("Compte non trouvé");
            }
            return compte.getSolde();
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            return -1;
        }
    }

    public List<Operation> getOperations(String codeCompte) {
        try {
            ValidationUtils.validerCodeCompte(codeCompte);
            Compte compte = comptes.get(codeCompte);
            if (compte == null) {
                throw new IllegalArgumentException("Compte non trouvé");
            }
            return compte.getOperations();
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void afficherDetailsCompte(String codeCompte) {
        try {
            ValidationUtils.validerCodeCompte(codeCompte);
            Compte compte = comptes.get(codeCompte);
            if (compte == null) {
                throw new IllegalArgumentException("Compte non trouvé");
            }
            compte.afficherDetails();
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public List<Compte> getTousLesComptes() {
        return new ArrayList<>(comptes.values());
    }
}