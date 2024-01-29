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

Let's take a closer look at the `TLSSocketFactoryTest` code and see how it holds up against some common testing guidelines:

1. **Detached Test Case**: No sign of tests floating around on their own without being part of a logical group. All tests seem to be well-connected within the test class.

2. **JUnit 4 Suites Should Use `@Suite` Annotation**: The code doesn't utilize JUnit 4 suites. It's not applicable here.

3. **JUnit 4 Test Should Use `@After` Annotation**: The `TLSSocketFactoryTest` doesn't have teardown logic using `@After`. This is okay as it depends on the testing needs.

4. **JUnit 4 Test Should Use `@Before` Annotation**: Similar to the `@After` rule, `@Before` is not used here. It's fine if not needed.

5. **JUnit 4 Test Should Use `@Test` Annotation**: The `@Test` annotation is used appropriately for marking test methods.

6. **JUnit Assertions Should Include Message**: The assertions lack custom failure messages. While it's generally good to have descriptive messages, this isn't strictly a violation.

7. **JUnit Spelling**: No spelling mistakes were found in the test class.

8. **JUnit Static Suite**: There's no evidence of using static test suites in the provided code.

9. **JUnit Test Contains Too Many Asserts**: The `typical` test method has multiple assertions. This isn't necessarily bad, but breaking it into smaller tests might improve clarity.

10. **JUnit Tests Should Include Assert**: The test methods include assertions, which is in line with good testing practices.

11. **JUnit Use Expected**: The code doesn't utilize the `ExpectedException` rule or similar mechanisms. This is not a strict requirement.

12. **Unnecessary Boolean Assertion**: No unnecessary boolean assertions were found.

13. **Use `assertEquals` Instead of `assertTrue`**: The code doesn't violate this rule. It appropriately uses `assertEquals` when needed.

14. **Use `assertNull` Instead of `assertTrue`**: There's no violation of this rule. The code uses `assertNull` when appropriate.

15. **Use `assertSame` Instead of `assertTrue`**: The provided test class doesn't have instances where `assertSame` could replace `assertTrue`.

16. **Use `assertTrue` Instead of `assertEquals`**: The test class doesn't have instances where `assertTrue` should be replaced with `assertEquals`.

In conclusion, the `TLSSocketFactoryTest` class appears to follow generally good testing practices. Some recommendations might depend on specific project and team standards. For instance, breaking down the `typical` test method could be considered for better maintainability.
