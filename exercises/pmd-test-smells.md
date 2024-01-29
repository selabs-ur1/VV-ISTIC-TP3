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

Authors: Dufeil Jaufret & Gentile Brian

I used the "JUnitTestContainsTooManyAsserts" with this command : 

```bash
$ pmd check -f html -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -d C:/Users/jaufr/Documents/VV/maths -r rendu.html
```

```java
@Test
    public void testRangeMultipleIterations() {
        // Check that we can iterate several times using the same instance.
        final int start = 1;
        final int max = 7;
        final int step = 2;

        final List<Integer> seq = new ArrayList<>();
        final IntegerSequence.Range r = IntegerSequence.range(start, max, step);

        final int numTimes = 3;
        for (int n = 0; n < numTimes; n++) {
            seq.clear();
            for (Integer i : r) {
                seq.add(i);
            }
            Assert.assertEquals(4, seq.size());
            Assert.assertEquals(seq.size(), r.size());
        }
    }
```    

__C:\Users\jaufr\Documents\VV\maths\commons-math\commons-math-legacy-core\src\test\java\org\apache\commons\math4\legacy\core\IntegerSequenceTest.java__  34  
Unit tests should not contain more than 1 assert(s).


Here we can see in this test that we have 2 assert so when an error occurs we don't know from which assert it comes. We can remove on them and make an other test for the other.