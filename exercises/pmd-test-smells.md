# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules
designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

### Test smells vues en cours

- TooManyAsserts
- UnnecessaryBooleanAssertion
- AssertTrueInsteadOfAssertEquals

### Test smell trouvé pour le TooManyAssert :

```java
class Test {
    @Test
    public void testAmbiguousLongWithoutEqualSingleDash() throws Exception {
        final String[] args = {"-b", "-foobar"};
        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasOptionalArg().create('f'));
        options.addOption(OptionBuilder.withLongOpt("bar").hasOptionalArg().create('b'));
        final CommandLine cl = parser.parse(options, args);
        assertTrue(cl.hasOption("b"));
        assertTrue(cl.hasOption("f"));
        assertEquals("bar", cl.getOptionValue("foo"));
    }
}
```

On remarque qu'il y a 3 asserts dans ce tests alors qu'il faut essayer d'en avoir 1 par test. Pour éviter ce test smell,
il est possible de diviser ce cas de test en plusieurs cas de tests.

Test corrigé :

```java
class Test {

    @Test
    public void testOptionBIsRecognized() throws Exception {
        final String[] args = { "-b", "-foobar" };
        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasOptionalArg().create('f'));
        options.addOption(OptionBuilder.withLongOpt("bar").hasOptionalArg().create('b'));
        final CommandLine cl = parser.parse(options, args);
        assertTrue(cl.hasOption("b"), "Option 'b' should be recognized");
    }

    @Test
    public void testOptionFIsRecognized() throws Exception {
        final String[] args = { "-b", "-foobar" };
        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasOptionalArg().create('f'));
        options.addOption(OptionBuilder.withLongOpt("bar").hasOptionalArg().create('b'));
        final CommandLine cl = parser.parse(options, args);
        assertTrue(cl.hasOption("f"), "Option 'f' should be recognized");
        assertEquals("bar", cl.getOptionValue("foo"), "Value for 'foo' should be 'bar'");
    }

    @Test
    public void testOptionFooHasCorrectValue() throws Exception {
        final String[] args = { "-b", "-foobar" };
        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasOptionalArg().create('f'));
        options.addOption(OptionBuilder.withLongOpt("bar").hasOptionalArg().create('b'));
        final CommandLine cl = parser.parse(options, args);
        assertEquals("bar", cl.getOptionValue("foo"), "Value for 'foo' should be 'bar'");
    }
}
```



