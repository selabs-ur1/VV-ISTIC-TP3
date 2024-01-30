# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Les "test smells" sont des motifs dans le code d'un test qui pourraient indiquer un problème ou une zone d'amélioration.

Roulette des Assertions :

Test Smell : Le test comporte plusieurs assertions, ce qui rend difficile de comprendre la cause exacte d'un échec.
Amélioration : Refactoriser le test en plusieurs tests plus petits, chacun se concentrant sur un comportement spécifique. Cela facilite l'identification de la partie du test qui échoue.

Test Pressé :

Test Smell : Le test tente de tester trop de fonctionnalités dans un seul cas de test.
Amélioration : Diviser le test en tests plus petits et plus ciblés. Chaque test devrait se concentrer sur la vérification d'un comportement ou d'un scénario spécifique.

Invité Mystère :

Test Smell : Le test utilise des ressources externes (par exemple, des fichiers, des bases de données) sans les configurer ou les nettoyer correctement.
Amélioration : Assurez-vous que le test configure toutes les ressources nécessaires avant de s'exécuter et les nettoie ensuite. Cela garantit que le test est isolé et ne dépend pas de facteurs externes.

Logique de Test Conditionnelle :

Test Smell : Le test contient une logique conditionnelle (instructions if) basée sur certaines conditions.
Amélioration : Si possible, refactoriser le test de sorte que chaque condition soit testée dans un cas de test séparé. Cela fournit une clarté sur les conditions qui provoquent les échecs de test.
