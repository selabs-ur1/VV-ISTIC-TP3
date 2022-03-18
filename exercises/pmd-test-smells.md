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

| Test smell | Rule| 
|------------|-----|
|Piggybacking|JUnitTestContainsTooManyAsserts|
|Happy Path|❌|
|Useless assert|UnnecessaryBooleanAssertion|


The Piggybacking could be detected by the rule 'JUnitTestContainsTooManyAsserts'

We di

We used the ruleset DetachedTestCase to detect the detached tests in the apache collection project

Resume of the analysis on the whole project

```text
Summary:

org.apache.commons.collections4.map.LazySortedMapTest : 3
org.apache.commons.collections4.functors.AbstractCompositePredicateTest : 1
org.apache.commons.collections4.keyvalue.AbstractMapEntryTest : 1
org.apache.commons.collections4.properties.EmptyPropertiesTest : 1
org.apache.commons.collections4.IterableUtilsTest : 2
```

Example of smell code :
```java=
public void getFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.get(bag, 0));
    }
```

This test is deatched, it uses assertions without junit Test or Ignore annotation, in order to correct this test smell we need to add @Test before the method if it's useful or @Ignore on the contrary

