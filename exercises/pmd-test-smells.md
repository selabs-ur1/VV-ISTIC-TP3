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


DetachedTestCase nécessite une intervention manuelle car il n'y a pas d'annotation associée. 
Les règles concernant l'utilisation d'annotations spécifiques dans les tests JUnit, telles que @Suite, @After, @Before, @Test,
ainsi que des aspects liés à l'orthographe correcte et à l'inclusion appropriée d'assertions, exigent également une intervention manuelle pour améliorer l'automatisation du processus de test.
L'indication de la présence de trop d'assertions dans les tests reflète une situation de "Free ride" ou "PiggyBack", où la complexité rend difficile l'identification de la cause d'une défaillance, tout en pouvant également correspondre à un test "Eager" impliquant un nombre excessif d'assertions et testant trop de fonctionnalités. 
Les règles liées à l'utilisation d'annotations "expected" et à des assertions spécifiques évoquent le Happy Path, où le programme est testé pour sa réaction en présence d'erreurs, dépassant le simple scénario prévu.
Enfin, l'utilisation de suites statiques dans les tests JUnit est associée à la pratique de tester des méthodes privées ou d'adopter une perspective "X-Ray specs".
