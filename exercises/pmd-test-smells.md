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

Ces règles PMD permettent de repérer des mauvaises pratiques dans les tests unitaires. Elles vérifient qu’on utilise bien les annotations JUnit comme @Test, @Before ou @After, qu’on évite les tests avec trop d’assertions ou sans assertions, et qu’on choisit les bonnes méthodes d’assertion (par exemple, préférer assertEquals à assertTrue quand c’est possible). L’idée, c’est d’écrire des tests plus clairs, plus faciles à maintenir et plus fiables.


La règle `UnitTestContainsTooManyAsserts` à trouvé "Unit tests should not contain more than 1 assert(s)." dans `src/test/java/org/apache/commons/collections4/trie/UnmodifiableTrieTest.java` du projet Apache Commons Collections.

```java
@Test
public void testUnmodifiable() {
    assertTrue(makeObject() instanceof Unmodifiable);
    assertTrue(makeFullMap() instanceof Unmodifiable);
}
```

Afin de ne plus avoir de bad smell, il faudrait séparer les deux assertions en deux tests différents.

```java
@Test
public void testUnmodifiable() {
    assertTrue(makeObject() instanceof Unmodifiable);
}

@Test
public void testFullMap() {
    assertTrue(makeFullMap() instanceof Unmodifiable);
}
```