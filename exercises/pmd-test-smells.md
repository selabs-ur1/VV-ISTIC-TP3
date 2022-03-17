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

Plusieurs mauvaises pratiques sont présentes dans le dossier pmd-documentation tel que l'emploi des annotations pour les tests ou encore la présence d'un trop grand nombre d'asserts dans un même test. Nous retrouvons aussi l'emploi d'un assert qui est plus conseillé qu'un autre : 
- UseAssertEqualsInsteadOfAssertTrue
- UseAssertNullInsteadOfAssertTrue
- UseAssertSameInsteadOfAssertTrue
- UseAssertTrueInsteadOfAssertEquals

Nous allons nous pencher sur la règle portant sur l'utilisation de trop d'asserts dans un test JUnit, aussi connu sous le nom piggybacking.  

*Commande :*  `pmd -d <source code folder> -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -format <output format>`

Commande sous Linux après s'être situé dans le fichier commons-math/commons-math-legacy/src/test/java/org: 
```
run.sh pmd -d ./ -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -f html > errorfolder.html
```
Plusieurs erreurs remontent pour les fichiers contenus dans le dossier test. Nous avons décidé de traiter celle portant sur : `commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/fitting/leastsquares/AbstractLeastSquaresOptimizerAbstractTest.java` où l'on peut constater la présence de deux cas de tests dans une seule méthode : 
```java
@Test
public void testIllConditioned() {
    LinearProblem problem1 = new LinearProblem(new double[][]{
            {10, 7, 8, 7},
            {7, 5, 6, 5},
            {8, 6, 10, 9},
            {7, 5, 9, 10}
    }, new double[]{32, 23, 33, 31});
    final double[] start = {0, 1, 2, 3};

    Optimum optimum = optimizer
            .optimize(problem1.getBuilder().start(start).build());

    Assert.assertEquals(0, optimum.getRMS(), TOL);
    assertEquals(TOL, optimum.getPoint(), 1, 1, 1, 1);

    LinearProblem problem2 = new LinearProblem(new double[][]{
            {10.00, 7.00, 8.10, 7.20},
            {7.08, 5.04, 6.00, 5.00},
            {8.00, 5.98, 9.89, 9.00},
            {6.99, 4.99, 9.00, 9.98}
    }, new double[]{32, 23, 33, 31});

    optimum = optimizer.optimize(problem2.getBuilder().start(start).build());

    Assert.assertEquals(0, optimum.getRMS(), TOL);
    assertEquals(1e-8, optimum.getPoint(), -81, 137, -34, 22);
}
```
Le correctif qui peut être apporté consiste tout simplement à scinder ce cas de test en deux, chacun contenant un des LinearProblem : 
```java
@Test
public void testIllConditionedFirstProblem() {
    LinearProblem problem1 = new LinearProblem(new double[][]{
            {10, 7, 8, 7},
            {7, 5, 6, 5},
            {8, 6, 10, 9},
            {7, 5, 9, 10}
    }, new double[]{32, 23, 33, 31});
    final double[] start = {0, 1, 2, 3};

    Optimum optimum = optimizer
            .optimize(problem1.getBuilder().start(start).build());

    Assert.assertEquals(0, optimum.getRMS(), TOL);
    assertEquals(TOL, optimum.getPoint(), 1, 1, 1, 1);
}

public void testIllConditionedSecondProblem() {
    LinearProblem problem2 = new LinearProblem(new double[][]{
            {10.00, 7.00, 8.10, 7.20},
            {7.08, 5.04, 6.00, 5.00},
            {8.00, 5.98, 9.89, 9.00},
            {6.99, 4.99, 9.00, 9.98}
    }, new double[]{32, 23, 33, 31});

    optimum = optimizer.optimize(problem2.getBuilder().start(start).build());

    Assert.assertEquals(0, optimum.getRMS(), TOL);
    assertEquals(1e-8, optimum.getPoint(), -81, 137, -34, 22);
}
```
