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

Règles retenues : 
JUnitAssertionsShouldIncludeMessage : bonne pratique pour avoir de l'information avant même d'examiner le code du test.  
JUnitTestContainsTooManyAsserts : meme logique que pour des mega methodes, plusieurs methodes specialisées sont plus explicites  
JUnitTestsShouldIncludeAssert : l'étape de l'oracle est cruciale lors de l'écriture de tests  


Les autres règles portent plutot sur des bonnes pratiques de nommage, l'utilisation des annotations, en bref une bonne utilisation de jUnit (savoir deleguer a jUnit par exemple avec assertEquals au lieu de   assertTrue(a.equals(b)), ce ne sont pas vraiment des "bad smells" dans un contexte de classe de test.  


Règle choisie pour l'exemple : JUnitTestsShouldIncludeAssert  

Résultat considéré : /home/gaby/VV/input_for_exo_2/commons-collections-master/src/test/java/org/apache/commons/collections4/bidimap/AbstractBidiMapTest.java:151:	JUnitTestsShouldIncludeAssert:	JUnit tests should include assert() or fail()  
/home/gaby/VV/input_for_exo_2/commons-collections-master/src/test/java/org/apache/commons/collections4/bidimap/AbstractBidiMapTest.java:156:	JUnitTestsShouldIncludeAssert:	JUnit tests should include assert() or fail()


Le code en question : 
```
 // testGetKey
    @Test
    public void testBidiGetKey() {
        doTestGetKey(makeFullMap(), getSampleKeys()[0], getSampleValues()[0]);
    }

    @Test
    public void testBidiGetKeyInverse() {
        doTestGetKey(
            makeFullMap().inverseBidiMap(),
            getSampleValues()[0],
            getSampleKeys()[0]);
    }

    private void doTestGetKey(final BidiMap<?, ?> map, final Object key, final Object value) {
        assertEquals(value, map.get(key), "Value not found for key.");
        assertEquals(key, map.getKey(value), "Key not found for value.");
    }
```

L'auteur n'a pas respecté les 3 étapes dans une méthode de test.  
testBidiGetKey() & testBidiGetKeyInverse() font office d'étapes d'initialisation et d'execution (ce que l'on veut tester).  
doTestGetKey est un oracle commun au deux méthodes précédentes.  
Dans certains cas, on pourrait faire une initialisation commune à toutes les méthodes de test avec @BeforeEach, si la classe de test est bien spécialisée.  
Mais ce n'est pas toujours possible, certains tests requierent une initialisation spécifique.  
Un meilleur découpage de ce code pourrait être :   

```
@Test
public void testBidiGetKey() {
	Object key = getSampleKeys()[0];
	Object value = getSampleValues()[0];
	BidiMap<?, ?> map =  makeFullMap(); 
	
	assertEquals(value, map.get(key), "Value not found for key.");
        assertEquals(key, map.getKey(value), "Key not found for value.");  
}

@Test
public void testBidiGetKeyInverse() {
	Object key = getSampleKeys()[0];
	Object value = getSampleValues()[0];
	BidiMap<?, ?> map =  makeFullMap().inverseBidiMap();
	
	assertEquals(value, map.get(key), "Value not found for key.");
        assertEquals(key, map.getKey(value), "Key not found for value.");
}
```





