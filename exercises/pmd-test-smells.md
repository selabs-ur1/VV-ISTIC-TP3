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

Commande exec pour trouver des erreurs :

```shell
pmd check -f text -R category/java/errorprone.xml -d /home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/ |grep DetachedTestCase > outputCommonsMath.txt
```

resultat :

```shell
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy-core/src/test/java/org/apache/commons/math4/legacy/core/MathArraysTest.java:712:	DetachedTestCase:	Probable detached JUnit test case.
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/linear/SingularValueDecompositionTest.java:169:	DetachedTestCase:	Probable detached JUnit test case.
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/linear/SingularValueDecompositionTest.java:202:	DetachedTestCase:	Probable detached JUnit test case.
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/stat/descriptive/rank/PercentileTest.java:251:	DetachedTestCase:	Probable detached JUnit test case.
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/stat/inference/KolmogorovSmirnovTestTest.java:145:	DetachedTestCase:	Probable detached JUnit test case.
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/stat/inference/KolmogorovSmirnovTestTest.java:370:	DetachedTestCase:	Probable detached JUnit test case.
/home/arthurlair/Documents/Universite/M2_ILA/ProjGit/VV/commons-math/commons-math-legacy/src/test/java/org/apache/commons/math4/legacy/stat/regression/MillerUpdatingRegressionTest.java:559:	DetachedTestCase:	Probable detached JUnit test case.
```

Le test smell "DetachedTestCase", identifié par PMD, résulte de l'omission de l'annotation indiquant qu'une méthode est un test. Cette négligence peut engendrer des problèmes d'isolation, créant des dépendances indésirables entre les tests et conduisant à des résultats imprévisibles. De plus, elle complique la maintenance en compromettant l'indépendance et l'autosuffisance des tests, pouvant affecter plusieurs parties du code. Les risques incluent des problèmes de cohérence dans les résultats des tests, affectant la confiance dans l'intégrité globale des tests. Dans un environnement Intégration Continue/Déploiement Continu, la présence de tests détachés est cruciale pour garantir la fiabilité des builds et des résultats des tests.



Code commons-math/commons-math-legacy-core/src/test/java/org/apache/commons/math4/legacy/core/MathArraysTest.java:712 :

```java
...
public void testConcatenateEmptyArguments() {
        final double[] x = new double[] {0, 1, 2};
        final double[] y = new double[] {3};
        final double[] z = new double[] {};
        final double[] u = new double[] {0, 1, 2, 3};
        Assert.assertArrayEquals(u,  MathArrays.concatenate(x, z, y), 0);
        Assert.assertArrayEquals(u,  MathArrays.concatenate(x, y, z), 0);
        Assert.assertArrayEquals(u,  MathArrays.concatenate(z, x, y), 0);
        Assert.assertEquals(0,  MathArrays.concatenate(z, z, z).length);
    }
...
```

Code de test amélioré :

```java
...
@Test
public void testConcatenateEmptyArguments() {
        final double[] x = new double[] {0, 1, 2};
        final double[] y = new double[] {3};
        final double[] z = new double[] {};
        final double[] u = new double[] {0, 1, 2, 3};
        Assert.assertArrayEquals(u,  MathArrays.concatenate(x, z, y), 0);
        Assert.assertArrayEquals(u,  MathArrays.concatenate(x, y, z), 0);
        Assert.assertArrayEquals(u,  MathArrays.concatenate(z, x, y), 0);
        Assert.assertEquals(0,  MathArrays.concatenate(z, z, z).length);
    }
...
```
