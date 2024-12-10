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

En utilisant PMD sur le projet Commons Collections avec la règle JUnitUseExpected (grâce à la commande pmd check -d <path to commons-collections> -R category/java/bestpractices.xml/JUnitUseExpected -f text), 
j'ai identifié plusieurs endroits où la gestion des exceptions dans les tests JUnit était réalisée manuellement via des blocs try-catch au lieu d'utiliser l'annotation @Test(expected = Exception.class).
Cette approche rend le code moins lisible et moins compréhensible.

Voici le message que nous à retourner PMD lors de l'utilisation :
In JUnit4, use the @Test(expected) annotation to denote tests that should throw exceptions

Et voici le code concerné :
try {
computeIfAbsent(keys[0], k -> newValues[0]);
fail("Expected IllegalArgumentException or UnsupportedOperationException on putIfAbsent (change)");
} catch (final IllegalArgumentException | UnsupportedOperationException ex) {
// ignore
}

Dans cet exemple, le test gère manuellement les exceptions attendues (IllegalArgumentException et UnsupportedOperationException) à l’aide d’un bloc try-catch.

## Improvements

Une piste d'amélioration consisterais à refactoriser le cas de test pour utiliser l’annotation @Test(expected = Exception.class) de JUnit 4. Cela simplifie le code et améliore sa lisibilité, car il devient évident qu’une exception est attendue
@Test(expected = IllegalArgumentException.class)
public void testMapComputeIfAbsentWithIllegalArgument() {
computeIfAbsent(keys[0], k -> newValues[0]);
}

