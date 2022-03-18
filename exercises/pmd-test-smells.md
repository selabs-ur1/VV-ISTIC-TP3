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
`pmd -d "C:\Users\boris\IdeaProjects\_Modules\V&V\commons-math\commons-math-legacy\src\test\java" -format html -R category/java/bestpractices.xml/UseAssertEqualsInsteadOfAssertTrue`

Après avoir cherché partout avec tous les tests dans les librairies apache j'ai fini par trouver cette miniscule erreur dans `commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\analysis\interpolation\AkimaSplineInterpolatorTest.java` a la ligne 249 :
```java
assertTrue( Precision.equals( expected, actual ) );
```

Cette erreur est probablement un faux positif pour que l'assert utilise une méthode equals non standard pour comparer les doubles.