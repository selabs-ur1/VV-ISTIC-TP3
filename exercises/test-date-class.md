# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1. 

Pour chaque méthode, nous avons fait des tests gérant les cas possibles avec certaines valeurs d'erreurs communes comme les Exceptions et au moins un cas par test qui passe ou ne passe pas. En soi ces tests utilisent souvent des dates et ont donc besoin de dates instanciées pour fonctionner, ils testent souvent sur les trois composantes du type Date, voire au moins sur l'une des composantes.

2. 
Grace au coverage de VSCode, nous obtenons un total de couverture de 76.85% qui est très satisfaisant.
Suite à quelques bugs dans les tests nous avons fait un "override" de "equals" pour pouvoir faire ce dont nous avions besoin.

3. 

Dans notre code il y a des fonctions contenant plusieurs opérateurs booléens, dont tous les cas ne sont pas forcément testés car les prérequis sont impossibles ou redondant. Notamment dans le cas de la fonction "isLeapYear()" où nous avons procédé à un raisonnement de logique pour trouver les cas à tester:

Q = a || (b && c)
=> a= year%400==0 , b= year%4==0 , c= year%100!=0
=> 0 équivaut à faux, et 1 équivaut à vrai

Q = a si: b=0 ou c=0
Q = b si: a=0 et c=1
Q = c si: a=0 et b=1
-----
abc

100 -> impossible, 2)
101 -> impossible, 1)
110 -> impossible, 2)

011 -> impossible, 3)
001 -> impossible, 1)

011 -> doublon
010 -> cas de test (Vrai et Faux)

1) impossible de trouver divisible par 100 sans trouver divisible par 4
    -> pas de X01
2) impossible, divisible par 400 force une fin de nombre en 00 donc divisible par 100
    -> pas de 1X0
3) Si divisible par 4 et 100, alors divisible par 400
    -> pas de 011

Le reste des cas représentaient des cas de tests redondants que nous avons décidé d'ignorer. Sinon nos cas de tests satisfont bien le "Base Choice Coverage" car nous testons les cas nécessaires.

4. 
Le rapport de PIT est disponible dans le dossier target/pit-reports.
Nous avons obtenu comme score de mutation: 83%, qui est très élevé.
Les types de "live mutants" relevés sont ceux-ci: "changed conditional boundary", "negated conditional", "replaced integer modulus with multiplication".

