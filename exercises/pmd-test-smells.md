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

Voici les règles pmd triées selon 3 catégories qui je trouve permet de voir les "categories" de code smells vu en cours.

- Avoir des tests utils et bien formé(simple et maintenable)
  - Un test doit être simple/court et ne vérifier qu'un comportement et ne doit donc pas contenir trop d'assertions.
  - Un test doit contenir au moins une assertion sinon il ne teste rien ou du moins pas clairement.
  - Ne pas utiliser AssertTrue avec une valeur boulenne comme true/false,pour provoquer une erreur volontairement. L'assertion est inutile car elle ne teste rien. Elle peut même engendrer des erreurs sur la qualité de la couverture.
  - Il faut préciser un message d'informations dans le assertEquals pour préciser ce qu'il fait.

- Utiliser les bonnes syntaxes selon les versions de Junit pour décrire un comportement.
  - Verification de l'utilisation de l'annotation @Test pour la déclaration d'un test. (Junit4 aussi)
  - En Junit4 Utilisation de l'annotation @SuiteClasses et @RunWith pour la déclaration d'une suite de test.
  - En Junit4 Utilisation de @Before pour une fonction de "setUp" avant tous les tests. Meme chose pour @After et les fonctions de "tearDown
  - En Junit4 il faut utiliser @test(expected) pour déclarer un test qui vérifie une exception plutot q'un try catch.
  - Verifier le nom de fonction classique qui pourrait être mal nommé (exemple setup au lieu de setUp)
  - La fonction suite() à d'une suite de teste doit etre public et static

- Utiliser des Asserts spécifiques quand c'est possible pour vérifier des propriétés. utiliser les Asserts spécialisés.
  - Utiliser AssertTrue/ AssertFalse plutot que AssertEquals pour des valeurs booleennes
  - Utiliser AssertSame pour vérifier l'égalités de références.
  - Utiliser AssertEquals pour vérifier l'égalité d'objet.
  - Utiliser AssertNull/ NotNull pour vérifier ce point plutot que AssertEquals ou AssertTrue.

### Lancement du test JunitTestContainsTooManyAssert sur Common Collections

Je n'ai pas réussi à lancer avec la commande fournit. J'obtenias une erreur sur le fait que la règles n'etait pas trouvé :

```bash
No rules found in rul more than 1 assert(s).
src/test/java/org/apache/commons/collections4/eset category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts
```

J'ai donc enlevé JunitTestContainsTooManyAsserts et est testé avec la commande suivante plus globale :

```bash
 pmd check -d src -R category/java/bestpractices.xml > report.txt
```

J'ai alors trouvé plusieurs erreures qui correspondent Mais sans le J de JUnit dans le nom voici quelques exemples :

```bash
src/test/java/org/apache/commons/collections4/set/UnmodifiableSortedSetTest.java:82: UnitTestContainsTooManyAsserts: Unit tests should not contain more than 1 assert(s). more than 1 assert(s).
src/test/java/org/apache/commons/collections4/
src/test/java/org/apache/commons/collections4/splitmap/TransformedSplitMapTest.java:101: UnitTestContainsTooManyAsserts: Unit tests should not contain more than 1 assert(s).
src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:65: UnitTestContainsTooManyAsserts: Unit tests should not contain more than 1 assert(s).

```

J'ai donc lancé cette commande pour récupérer ces codeSmell en particulier :

```bash
pmd check -d src -R category/java/bestpractices.xml/UnitTestContainsTooManyAsserts > report.txt
```

Il y avait ce smell de nombreuses fois dans common collections (plus de 1300 fois).
Cela est du au fait que la règle est restrictive à 1 assert. On peut imaginer accepter 3 asserts dans un meme cas de test car cela peut être pertinant pour vérifier plusieurs propriétes d'un élément (taille et contenu d'une liste) pour une même action. Cela permettrait d'être plus attentif aux réels cas posant problème.
