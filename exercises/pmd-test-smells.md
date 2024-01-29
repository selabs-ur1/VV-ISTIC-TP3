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

We used this command to check test smell:

```pmd check -d D:\Downloads\commons-math-master -R category/java/errorprone.xml -r result.html```

Here, pmd found a violation : 

```D:\Downloads\commons-math-master\commons-math-master\commons-math-legacy-core\src\test\java\org\apache\commons\math4\legacy\core\MathArraysTest.java:336: AvoidDuplicateLiterals: The String literal "Expecting NullPointerException" appears 4 times in this file; the first occurrence is on line 336 ```

And the code is : 
```
try {
            MathArrays.checkPositive(nullArray);
            Assert.fail("Expecting NullPointerException");
        }
```

The string should be defined somewhere and not overwritten.
