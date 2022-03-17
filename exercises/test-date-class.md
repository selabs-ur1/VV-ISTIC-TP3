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

##### 1. Utilisation de l'input space partitioning 
Pour chacune des méthodes présentées dans la classe Date nous nous appuyons sur les spécifications données par le TP pour établir nos premiers tests. Ces derniers tiennent compte des différents cas de figure présentés pour les dates. Il faut donc renseigner pour une date : un jour, un mois et une année. Ainsi, la méthode isValid regroupe les différentes possibilités de Date, qui se retrouve être communes avec d'autres méthodes. Nous retrouvons ainsi les blocs suivants : 
- les années négatives / les années positives
- les mois négatifs / les mois positifs
- les jours négatifs / les jours positifs
- les mois contenant 31 jours :  {1, 3, 5, 7, 8, 10, 12}
- les mois contenant 30 jours : { 4, 6, 9, 11}
- le mois de Février {2} avec deux résultats possibles : 28 ou 29 jours. 

Nous allons donc établir dans un premier temps deux tests pour chaque spécification présentée par le TP, l'une reprenant les données valides, l'autre celles non valides. Nous nous assurons aussi que les tests qui doivent échouer lèvent les bonnes exceptions. 

##### 2. Evaluation de la couverture des instructions des cas de test
Avec la première implémentation des tests nous arrivons à un taux de 100% des méthodes qui sont couvertes, ce qui correspond à 100% des lignes du code.
Néanmoins, certains cas de figures n'ont pas été rajoutés, notamment tout ce qui concerne l'emploi de dates se déroulant avant l'an 0. Ainsi de nouveaux asserts vont être rajoutés dans le code afin de compléter les cas de test déjà implémentés. 

##### 3. Prédicat utilisant plus de deux opérateurs booléens
Dans le cas de la vérification de la validité de la date nous employons plusieurs opérateurs booléens à la suite. Afin de satisfaire le taux de couverture, nous avons, pour chaque situation possible, réalisé un test où nous venions modifier soit la validité du jour, soit du mois ou soit de l'année. La logique derrière était de porter notre attention sur ces trois paramètres qui sont essentiels dans la construction d'une date. Afin de s'assurer de lever une exception en cas de date invalide nous allons reprendre ces mêmes éléments pour les implémenter dans la classe de test. A noter que dans le cas de l'année il n'est pas possible d'avoir une donnée fausse car ce paramètre n'est pas contraint par des int et peut aussi être de valeur négative. 

##### 4. Utilisation de PIT pour évaluer la suite de test
Avec cette suite de cas de test nous arrivons à un taux de 89% pour le taux de couverture employant la mutation. 
Ainsi 46 mutants ont été créés et 41 ont été tués. 
Ceux qui n'ont pas été tués portent sur le test de la méthode isValid avec des conditional boundary qui n'ont pas été détectés. Nous allons donc rajouter quelques tests afin de nous assurer de tester les jours limites de certains mois : 
```java
public void testIsValidDateFebruaryDayLimit(){
    (...)
        assertTrue(Date.isValidDate(01,1,2022));    
}

public void testIsValidDateDayLimit(){
    (...)
     assertTrue(Date.isValidDate(01,5,2022));
}

public void testIsNotValidDateLeapYear(){
        assertFalse(Date.isValidDate(30,1,2020));
    }
```
Avec l'ajout de ces trois tests nous avons maintenant 45 mutants tués et un taux de couverture de 98%. A la lecture du rapport, il semble que les deux mutants portant sur les lignes du mois de février ne sont pas détruits malgré l'ajout de nouveaux tests afin de couvrir le plus de cas de figure. Néanmoins nous avons couvert les exceptions et vérifier le bon fonctionnement de cette partie du code précédemment et nous atteignons au total une couverture du code de 100% et une couverture concernant le mutator de 96%.
