# Projet : Gestion de comptes bancaires

## Description du projet
Ce projet consiste à développer une application console en **Java 8** pour gérer les comptes bancaires d'une banque.  
L'application permet de créer différents types de comptes (courant et épargne), d'effectuer des opérations bancaires (versements, retraits, virements), et de consulter les informations des comptes.

Le système est conçu selon les principes de la **programmation orientée objet** et respecte les bonnes pratiques **SOLID**.

---

## Technologies utilisées
- Java 8  
- Eclipse IDE  
- Collections Java : `ArrayList`, `HashMap`  
- Java Time API pour la gestion des dates  
- UUID pour l'identifiant unique des opérations  
- Git pour le contrôle de version  

---

## Structure du projet
Le projet est organisé en plusieurs packages :

1. **models** : contient les classes principales du domaine bancaire  
   - `Compte` (abstraite)  
   - `CompteCourant`  
   - `CompteEpargne`  
   - `Operation` (abstraite)  
   - `Versement`  
   - `Retrait`  

2. **service** : contient la classe `ServiceBancaire` qui gère la logique métier  
   - Gestion des comptes et des opérations  
   - Création, dépôt, retrait et virement  

3. **presentation** : contient les classes pour l'interface utilisateur  
   - `ApplicationBancaire` : menu interactif pour l'utilisateur  
   - `Main` : point d'entrée de l'application  

4. **utilitaire** : contient les classes utilitaires  
   - `ValidationUtils` : validations des entrées  
   - `CodeGenerator` : génération des codes de compte  

---

## Prérequis
- Java JDK 8 installé  
- IDE Eclipse ou tout autre IDE Java  
- Connaissance de base en programmation orientée objet  

---

## Fonctionnalités principales
- Créer un compte courant ou un compte épargne  
- Effectuer un versement dans un compte  
- Effectuer un retrait d'un compte  
- Effectuer un virement entre comptes  
- Consulter le solde d'un compte  
- Consulter les opérations d'un compte  
- Voir la liste de tous les comptes  

---

## Présentation du projet
Une présentation visuelle du projet est disponible sur Canva :  
[Voir la présentation](https://www.canva.com/design/DAGzbwMg4pA/zfum1ZBeXQyFYn4wPlfQjA/edit?utm_content=DAGzbwMg4pA&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

Cette présentation contient :  
- Le contexte du projet  
- La structure de l'application  
- Les classes principales et leur rôle  
- Les fonctionnalités principales  
- Un aperçu du menu et de l'interface utilisateur

---

## Gestion de projet (Jira)
Le suivi des tâches et la gestion du projet ont été réalisés avec **Jira**.  
👉 [Accéder au tableau Jira](https://ichrakjaifra-1758033929972.atlassian.net/jira/software/projects/SCRUM/boards/1?atlOrigin=eyJpIjoiODlmMmY3YmE5YTQ4NDJkN2FmNTRkYjgwNjFjOGQyYjEiLCJwIjoiaiJ9)  

---

## Instructions d'installation et d'exécution
1. Cloner le dépôt GitHub :  
   ```bash
   git clone https://github.com/ichrakjaifra/SystemeBancaire.git
