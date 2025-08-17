## Configuration des variables d'environnement

Avant de lancer l'application, vous devez définir les variables d'environnement suivantes :

A la racine du projet créez un fichier `.env` et ajoutez les lignes suivantes :
```bash
DB_URL=jdbc:mysql://localhost:3306/printable_db?useSSL=false&serverTimezone=UTC
DB_USER=root
DB_PASS=votre_mdp
```





# 📦 Base de données du projet

Ce dépôt contient :
- la **structure** de la base de données (tables, clés, relations),
- des **données** (dont des images en BLOB) à importer correctement pour éviter les problèmes d’affichage.

---

## 🗂 Arborescence

/db/ -> script(s) SQL de création de la base (structure uniquement)

db/data/ -> fichier(s) SQL de données à importer via MySQL Workbench



---

> Les fichiers du dossier `/data` contiennent des images (BLOB). **Ils doivent être importés via `Server > Data Import` dans MySQL Workbench.**  
> Ne pas exécuter comme de simples scripts (`Run`) sinon les images ne fontionneront pas.

---

## 🧪 Test du programme

Une fois la base importée et le projet lancé, vous pouvez tester le programme en créant un compte ou en se connectant avec le compte administrateur suivant :

- **Login :** `admin@admin.admin`
- **Mot de passe :** `AdminAdmin`

Cela permet d’accéder a l'éditeur de code promo.