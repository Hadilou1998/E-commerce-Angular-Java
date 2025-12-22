# E-commerce-Angular-Java
### Technologies : Angular / Java (Spring Boot) / MySQL

---

## 1. Objectif du projet
Mettre en place une plateforme e-commerce complète, permettant :
-   La consultation et l'achat de produits en ligne.
-   La gestion des clients, commandes et paiements.
-   L'administration du catalogue et des ventes via une interface sécurisée.
-   Une architecture moderne basée sur une API REST.

---

## 2. Architecture technique
L'application est structurée en plusieurs parties :
-   **Frontend (Angular)**:
    -   Angular
    -   TypeScript
    -   HTML / CSS
    -   Bootstrap
-   **Backend (Java Spring Boot)**:
    -   Java
    -   Spring Boot
    -   Spring Security (JWT)
    -   Spring Data JPA
-   **Database (MySQL)**:
    -   Stockage des informations sur les produits, clients, commandes, etc.
-   **Conteneurisation (Docker)**:
    -   Docker
    -   docker-compose
-   **Gestion du code**: 
    -   Git / Github.
-   **Déploiement**: 
    -   Docker (avec un dossier `docker/` contenant les fichiers).
    -   Lancement de l'application avec `docker-compose up --build`.

---

## 3. Fonctionnalités principales
### 3.1 Gestion des utilisateurs (clients)
-   Inscription et connexion des utilisateurs.
-   Authentification sécurisée par JWT.
-   Gestion du profil utilisateur.
-   Historique des commandes.

### 3.2 Gestion des produits
-   CRUD des produits (admin).
-   Gestion des catégories.
-   Gestion du stock.
-   Ajout d'images produit.
-   Recherche et filtrage (prix, catégorie, nom).

### 3.3 Gestion du panier
-   Ajout de produits au panier.
-   Modification des quantités.
-   Suppression d'articles.
-   Calcul automatique du total.

### 3.4 Gestion des commandes
-   Validation du panier.
-   Création de commande.
-   Suivi du statut :
    -   En attente.
    -   Payée.
    -   Expédiée.
    -   Livrée.
-   Historique des commandes client.

### 3.5 Paiement
-   Simulation de paiement ou intégration d'API (Stripe / PayPal).
-   Vérification de paiement avant validation de la commande.

### 3.6 Tableau de bord administrateur
-   Gestion des utilisateurs.
-   Gestion des produits et catégories.
-   Gestion des commandes.
-   Statistiques :
    -   Chiffre d’affaires
    -   Nombre de commandes
    -   Produits les plus vendus

---

## 4. Modèle de données

Users
-   id
-   firstname
-   lastname
-   email
-   password
-   role (user, admin)

Products
-   id  
-   name
-   description
-   price
-   stock
-   image
-   category_id

Categories
-   id
-   name
-   description

Cart
-   id
-   user_id

Cart_Items
-   id
-   cart_id
-   product_id
-   quantity

Orders
-   id
-   user_id
-   total_price
-   status
-   created_at

Order_Items
-   id
-   order_id
-   product_id
-   quantity
-   price

---

## 5. Endpoints API (REST)

Authentification
-   POST /auth/register -> inscription
-   POST /auth/login -> connexion (JWT)

Produits
-   GET /products -> liste des produits
-   GET /products/{id} -> détails produit
-   POST /products -> ajouter un produit (admin)
-   PUT /products/{id} -> modifier un produit (admin)
-   DELETE /products/{id} -> supprimer un produit (admin)

Catégories
-   GET /categories
-   POST /categories (admin)

Panier
-   GET /cart
-   POST /cart/items
-   PUT /cart/items/{id}
-   DELETE /cart/items/{id}

Commandes
-   POST /orders -> créer une commande
-   GET /orders -> commandes utilisateur
-   GET /orders/{id} -> détails commande
-   PUT /orders/{id}/status -> modifier statut (admin)

---

## 6. Sécurité
-   Authentification JWT.
-   Spring Security.
-   Gestion des rôles :
    -   user
    -   admin
-   Chiffrement des mots de passe (BCrypt).
-   Validation des données (DTO + annotations).

---

## 7. Déploiement

Dossier docker/

Contient :
-   docker-compose.yml
    -   frontend (Angular)
    -   backend (Spring Boot)
    -   database (MySQL)
-   Dockerfile Angular
-   Dockerfile Spring Boot
-   Volumes persistants pour MySQL