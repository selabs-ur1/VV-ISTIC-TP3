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

J'ai lancé la commande `pmd-bin-6.43.0\bin>pmd.bat -d git\commons-collections\src\test\java -R category/java/bestpractices.xml/JUnit4TestShouldUseAfterAnnotation`.
Et j'ai eu 2 test smell. l'un est : `git\commons-collections\src\test\java\org\apache\commons\collections4\map\AbstractMapTest.java:2010:  JUnit4TestShouldUseAfterAnnotation:     JUnit 4 tests that clean up tests should use the @After annotation, JUnit5 tests should use @AfterEach or @AfterAll`.
Ici la méthode tearDown() est toujours utilisé pour nettoyer les données alors qu'il faut utiliser l'annotation @After ou @AfterEach, @AfterAll
