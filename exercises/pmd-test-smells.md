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

Nous avons vu le fait qu'un test doit être annoté **@Test et @Ignore** (cf DetachedTestCase.md &
JUnit4TestShouldUserTestAnnotation.md et diapo 40 du cours).

Nous avons vu le fait qu'une classe de test doit contenir **@Before et @After**
avec les méthodes **setUp() et tearDown()** (cf JUnit4TestShouldUserAfterAnnotation.md & cf
JUnit4TestShouldUserBeforeAnnotation.md & JUnitSpelling et diapo 30 du cours).

Dans le cours, nous avons vu qu'un test devait contenir un oracle avec des assertions
(cf JUnitTestsShouldIncludeAssert.md et diapo 37)

Nous avons travaillé l'annotation @Test(expected=NomClass.class) (cf JUnitUseExpected et diapo
40)


Nous avons choisi la règle du JUnitSpelling. Nous avons appliqué cette règle sur la classe JdkMathtest.java.
Nous nous sommes rendu compte qu'il n'y avait pas de méthode setUp et tearDown. Nous pouvons les rajouter de
manière à initialiser des variables globales. Probablement, ils ne l'ont pas fait car n'ont pas considéré comme
étant utiles. Nous aurions pu mettre en variables globales les variables **runtimeVersion** et **doTest**
pour qu'elles puissent être utilisées dans d'autres méthodes de test.


    private final String runtimeVersion;
    private final boolean doTest;

    @Before
    public void setUp(){
        runtimeVersion = System.getProperty("java.runtime.version");
        doTest = runtimeVersion.matches("^1\\.8\\..*");
    }

    @After
    public void tearDown(){
    }
