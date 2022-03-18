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

Après avoir executé plusieurs des méthodes de `pmd-documentation`, voici la règle qui a détecté un bad smell : UseAssertSameInsteadOfAssertTrue avec la ligne de commande : 
`pmd -d D:\IntelliJ_Projects\commons-collections\src\test\java -format html -R category/java/bestpractices.xml/UseAssertSameInsteadOfAssertTrue > error.html`

Le bad smell est détecté dans la library Apache Collections, dans la classe de test `MultiKeyTest.java`, se trouvant ici : `commons-collections\src\test\java\org\apache\commons\collections4\keyvalue\MultiKeyTest.java`.
Voici la ligne de code concernée : 
```java
assertTrue(mk1.hashCode() != mk3.hashCode());
```
Ce bad smell est du à un usage de assertTrue sur une comparaison de valeur, ce qui n'est pas optimal, puisqu'il existe des assertions qui compare déjà des valeurs (comme assertSame). Cet usage est d'ailleurs déprécié depuis PMD 6.37.0.

Voici une proposition de fix avec assertSame :

```java
assertSame(mk1.hashCode(), mk3.hashCode());
```
