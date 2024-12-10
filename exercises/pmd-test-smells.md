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

Voici le test smell que nous avons trouvé : 

commons-collections-master/src/test/java/org/apache/commons/collections4/multimap/AbstractMultiValuedMapTest.java:660:	DetachedTestCase:	Probable detached JUnit test case.

et cela correspond a ce test : 

    /**
     * Resets the {@link #map} and {@link #confirmed} fields to full.
     */
   
    public void resetFull() {
        map = makeFullMap();
        confirmed = makeConfirmedMap();
        final K[] k = getSampleKeys();
        final V[] v = getSampleValues();
        for (int i = 0; i < k.length; i++) {
            confirmed.put(k[i], v[i]);
        }
    }

On peut voir que c'est un méthode utilitaire et donc selon la règle de pmd elle devrait être en private.
    
Il suffit donc de changer sa visibilité a private pour se conformer à la règle de pmd. 
    
    private void resetFull() 