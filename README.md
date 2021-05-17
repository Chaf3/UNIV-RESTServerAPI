**********************************************************************************************
                                    PROJET API REST
**********************************************************************************************

Le but de l’exercice était d’implémenter un serveur de calcul en respectant tous les principes vus en cours sur les services de type REST, tout en respectant les fondamentaux du client-serveur. 
L'intégralité du projet a été réalisé via Netbeans et l'outil TomCat.

**********************************************************************************************
				                ARCHITECTURE DU PROJET
**********************************************************************************************

Dans le répertoire suivant, vous retrouverez deux projets netbeans :

    - Un premier projet Netbeans contenant l’application de la servlet à lancer. Il y a également une
page html pour pouvoir tester le service.
    - Un deuxième projet Netbeans contenant le client et les dataclass.

Pour lancer le projet, il faut d’abord lancer la servlet et ensuite lancer le client en ajustant votre adresse locale dans les paramètres du projet. (Properties -> Run et changez le localhost :8080 par l’adresse qui correspond à votre machine). 
Attention !  Il se peut que vous ayez une erreur lorsque vous importerez le projet sur Netbeans. En effet, une erreur est provoquée avec le jar JSon, ce qui empêche la compilation. Pour résoudre ça, il faut importer à nouveau le jar JSon et ensuite aller dans les propriétés pour supprimer l’erreur directement (gson jar broken).

Au lancement du client, une boucle de calcul se lance et vous devez saisir vos paramètres sous la
forme :

**********************************************************************************************
Valeur1 operande Valeur2, avec un espace entre chaque élément. 
Si vous voulez réaliser une opération unaire, il faut juste (racine ou inverse ici) mettre 0 pour val2.
**********************************************************************************************

Exemple opération unaire : 25 racine 0 ou 14 inverse 0.
Exemple opération simple : 14 + 36 ou 147 * 6.

Pour stopper la boucle de calcul, il vous suffit d’entrer « stop » et le service s’interrompt.
Côté client, le seul test effectué se fait sur le nombre d’argument passé par l’utilisateur. Je
reconstruit l’URL en reproduisant les paramètres d’une méthode GET classique.
En revanche, côté serveur, plusieurs tests sont effectués :
    -  Test sur les arguments
    - Test sur les valeurs des arguments
    - Test sur l’opérande passé en paramètre ( ex : détection de la division par 0 )

Le résultat est toujours affiché dans le bon format. Les données sont transmises grâce à des dataclass, qui affiche le résultat et qui fournissent le code
d’erreur demandé. 