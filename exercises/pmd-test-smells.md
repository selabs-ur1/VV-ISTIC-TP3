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
 Le test smells PiggyBack vu en cours consiste à mettre trop d'assertion dans un test plutôt que de créer plusieurs test. Ce test smell est testé par la règle JUnitTestContainsTooManyAssert.
 
 ###Utilisation
````
pmd.bat -d commons-math-master -R category/java/errorprone.xml/DetachedTestCase
````
````
C:\Users\jules\Documents\TRAVAIL\M2 ILa\V&V\commons-math-master\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\linear\SingularValueDecompositionTest.java:172:  DetachedTestCase:       Probable detached JUnit test case.
````

Test concerné :
````java
/** test matrices values */
    // This test is useless since whereas the columns of U and V are linked
    // together, the actual triplet (U,S,V) is not uniquely defined.
    public void testMatricesValues1() {
       SingularValueDecomposition svd =
            new SingularValueDecomposition(MatrixUtils.createRealMatrix(testSquare));
        RealMatrix uRef = MatrixUtils.createRealMatrix(new double[][] {
                { 3.0 / 5.0, -4.0 / 5.0 },
                { 4.0 / 5.0,  3.0 / 5.0 }
        });
        RealMatrix sRef = MatrixUtils.createRealMatrix(new double[][] {
                { 3.0, 0.0 },
                { 0.0, 1.0 }
        });
        RealMatrix vRef = MatrixUtils.createRealMatrix(new double[][] {
                { 4.0 / 5.0,  3.0 / 5.0 },
                { 3.0 / 5.0, -4.0 / 5.0 }
        });

        // check values against known references
        RealMatrix u = svd.getU();
        Assert.assertEquals(0, u.subtract(uRef).getNorm(), normTolerance);
        RealMatrix s = svd.getS();
        Assert.assertEquals(0, s.subtract(sRef).getNorm(), normTolerance);
        RealMatrix v = svd.getV();
        Assert.assertEquals(0, v.subtract(vRef).getNorm(), normTolerance);

        // check the same cached instance is returned the second time
        Assert.assertSame(u, svd.getU());
        Assert.assertSame(s, svd.getS());
        Assert.assertSame(v, svd.getV());

    }
````

Comme précisé en commentaire, ce test est inutile et n'est donc pas utilisé, c'est pour cela que l'annotation @Test est absente.
Pour respecter la convention, cette annotation devrait être présentes et suivie de l'annotation @Ignore.