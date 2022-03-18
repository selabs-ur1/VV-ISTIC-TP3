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

On common-collections with JUnitAssertionsShouldIncludeMessage, `./run.sh pmd -R category/java/bestpractices.xml/JUnitAssertionsShouldIncludeMessage -d ../../commons-collections/src/` i find these result :

```
...
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:349:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:360:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:361:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:363:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:368:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:369:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:382:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
/home/tanguy/Téléchargements/commons-collections/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:383:	JUnitAssertionsShouldIncludeMessage:	JUnit assertions should include a message
...
```
There is a lot of assertions without messages by Patricia
The improvement will be to write what an assertion should have as message.