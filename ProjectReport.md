# Projet Application WEB Orientée Services
![Screenshot](DauDau.png)

# Developpeurs : Henri Aycard (@HenryAycard) & Antoine Perrin (@ahgperrin)
# Cadre : Obtention du Master 2 MIAGE Informatique pour la Finance
# Enseignants : Mouloud Menceur
# Soutenance : Lundi 5 Juillet 13h30
# Langage : JAVA
# Stack : SpringBoot / Docker / JUnit


# Présentation du projet 
Ce projet prend par au seiun de notre formation à l'université Paris Dauphine En miage INformatique pour la finance. 
L'objectif pédagogique du projet est de manipuler la stack SpringBoot Java et d'apprendre à creer des microservices WEB.
Au sein de ce projet nous avons choisi de réaliser deux Microservices, TauxChange et OperationChange. Notre Application
se présente sous la forme d'un bureau de change en ligne accésible en ligne de commande.
Dans cette partie nous allons simplement présenter de manière brève les deux microservices et le processus d'installation.
Un fichier README.md est disponible pour chaque microservice au sein de leur Repo respectifs.
Nous avons choisi d'aggreger le README.md de chaque microservices sous celui-ci en guise de documentation complète.

## Taux de change
Ce microservice a pour but de publier et stocker les taux de change par date au sein d'une base de données sous H2.
Le taux de change est exprimé dans une devise vers l'autre devise avec une date et chaque entrée a un Id clé primaire unique.
Le microservices Taux de change est disponible sous : 
```yaml
https://github.com/HenriAycard/Microservice-TauxChange.git
```
Le microservice doit être cloné en local:

```yaml
git clone https://github.com/HenriAycard/Microservice-TauxChange.git
```
Il peut être installé et lancé selon la procédure spécifique du README.md TauxChange,
ou être ouvert dans IntelliJ via pom.xml as a project et lancé via TauxChangeApplication sous main/java/Repository
Une fois lancé , il suffit d'ouvrir un terminal et d'ecrire les requêtes directement dedans.

## Operation de change
Ce microservice a pour but de gérer les opérations de change effectuées et de les stocker dans une base de données sous H2.
L'objectif est de pouvoir accéder aux opérations de change via des requêtes en ligne de commandes. Chaque opération est identifiée
via son Id clé primaire, et comporte des informations comme le taux, la contrepartie de l'opération, la paire de devises, le taux ou 
bien encore la date.
Si le taux n'est pas spécifié il est automatique recupéré depuis le microservice TauxChange, si ce dernier est lancé sur la machine.

Le microservices Operation de change est disponible sous :
```yaml
https://github.com/HenriAycard/Microservice-OperationChange.git
```
Le microservice doit être cloné en local:

```yaml
git clone https://github.com/HenriAycard/Microservice-OperationChange.git
```
Il peut être installé et lancé selon la procédure spécifique du README.md OperationChange,
ou être ouvert dans IntelliJ via pom.xml as a project et lancé via OperationChangeApplication sous main/java/Repository
Une fois lancé , il suffit d'ouvrir un terminal et d'ecrire les requêtes directement dedans.

## Test

Nos deux microservices possèdent des tests unitaires dans leur repo test. Ces derniers fonctionnent et peuvent être lancés
via test/controller/ChangeControllerTest.

## Docker 


## Minikube
Nous n'avons pas obtenu de résultats a présenter sous minikube.