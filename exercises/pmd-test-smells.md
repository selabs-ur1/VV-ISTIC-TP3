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

La règle JUnitTestContainsTooManyAsserts évoque la test smell piggyback. 

J'ai utilisé la règle pmd JUnitAssertionsShouldIncludeMessage sur le module java common-math pour détecter les cas de tests n'étant pas assez "documenté". Un très grand nombre de tests "défectueux" ont été détecté. Il semblerait que l'assertEquals avec deux paramètres ait été plus utilisé pour tester le projet.

````java
    //BAD
    @Test
    public void testAdd() {
        BigReal a = new BigReal("1.2345678");
        BigReal b = new BigReal("8.7654321");
        Assert.assertEquals(9.9999999, a.add(b).doubleValue(), 1.0e-15);
    }
    
    //BETTER
    @Test
    public void testAdd() {
        BigReal a = new BigReal("1.2345678");
        BigReal b = new BigReal("8.7654321");
        Assert.assertEquals("Message expliquant le fail", 9.9999999, a.add(b).doubleValue(), 1.0e-15);
    }
````