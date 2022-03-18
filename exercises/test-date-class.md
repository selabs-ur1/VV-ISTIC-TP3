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

1) Dans un premier temps, nous allons créer des sets d'input pour chaque méthode:

Pour la création de date **Date(int day, int month, int year)** :

- 0 < day <= maximum de jour dans le mois (attention aux années bissextiles : 28 ou 29 pour février)

- 0 < month <= 12

- Pas de restriction sur l'année

**isValidDate()** est similaire, de toute manière, nous appelerons la méthode isValidDate() dans le constructeur.

**isLeapYear()** : pas de restriction sur l'année, il faut faire un calcul mathématique.

**nextDate()** : 
- le dernier jour du mois doit faire augmenter le mois et reset le jour à 1, de même pour le mois qui
augmentera l'année et reset le mois à 1.

- Dans le cas d'une année bissextile, le jour suivant du 28 février est le 29 tandis que 1er mars dans
le cas contraire.

- Les autres jours sont juste augmentés de 1.


Inversement pour **previousDate()** :
- le premier jour du mois deviendra le dernier jour du mois précédent, de même pour le mois qui deviendra
décembre et l'année l'année précédente.

- Dans le cas d'une année bissextile, le jour précédent celui de 1 er mars est le 29 février et le 28 
dans le cas contraire.

- Les autres jours sont juste diminués de 1.


**compareTo()** :
- 2 dates égales --> 0
- vérifier l'année en premier, le mois puis le jour : plus grand --> 1, inférieur --> -1.

2) Pour tester la validité de la date, nous avons créé 2 méthodes qui passent directement 
par le constructeur et qui appelle **isValidDate()** :

**Tests avec des dates incorrectes** 
- **testDateUncorrectDays()** : teste 2 dates avec des jours invalides (négatif et supérieur au jour max du mois)
- **testDateUncorrectMonths()** : teste 2 dates avec des mois invalides (négatif et supérieur à 12) 
- **testDateUncorrectDateForLeapYear()** : teste le 29 février pour une année non bissextile

Elles lancent des exceptions.
- **testIsValidDateNOkBadMonths()** && **testIsValidDateNOkBadDays()** : pour être sûr, nous avons
retesté isValidDate() avec des jours et mois incorrects.

**Tests avec des dates correctes** 
- **testIsValidDateOk()** : teste 2 date valides random et le 29 février pour une année bissextile

**Tests isLeapYear()**
- **testIsLeapYearOK()** : Teste avec 2020
- **testIsLeapYearNOK2019()** : Test avec 2019

**Tests nextDate()** 
- **testNextDateOKSimpleExample()** : teste le jour suivant du 15 février 2020 --> 16 Février 2020
- **testNextDateOKEndOfMonth()** : teste le jour suivant du 31 janvier 2020 --> 1 Février 2020
- **testNextDateOKEndOfYear()** : teste le jour suivant du 31 décembre 2020 --> 1 er Janvier 2021
- **testNextDateOKLeapYear()** : teste le jour suivant du 28 Février 2020 (dans le cas d'une année bissextile)
--> 29 Février 2020
- **testNextDateOKNotLeapYeap()** : teste le jour suivant du 28 Février 2019 (cas d'une année non bissextile)
--> 1 er mars 2019

**Tests pour previousDate()**

Nous avons repris globalement le principe des tests exécutés pour nextDate() mais adaptés pour previous.

**Tests pour compareTo()**
- **testCompareDateOKPosteriorDateYear()** : teste avec un année supérieure
- **testCompareDateOKPosteriorDateMonth()** : teste avec une année égale et un mois supérieur
- **testCompareDateOKPosteriorDateDay()** : teste avec un jour supérieur et mois et années égales
- **testCompareDateOKEqualsDate()** : 2 dates égales

A cette étape, nous avons une couverture de code de 97% (lignes couvertes) en sachant que nous n'avons
pas testé les getters. Nous avons testé les cas extrêmes, considérant que les cas nominaux pouvaient être
couverts par un seul test (ex : nextDate() pour le 15 février donne 16 février --> Cela généralise pour
les autres cas ou 1 <= day < max(jours dans le mois).

3) Tous les tests que nous avons faits n'ont pas d'opérateurs booléens multiples. Chaque assertion que nous
avons faites est indépendante des autres même si elle peut être liée.

4) Nous avons lancé la commande suivante :

    
    mvn test-compile org.pitest:pitest-maven:mutationCoverage

Notre score de couverture de mutation est de 83% ce qui relativement correct. Par exemple, nous avons
beaucoup de mutants au niveau de ces lignes : 

    return (month == 2 && isLeapYear(year) && day > 0 && day <= 29) || (day > 0 && day <= numberOfDaysInEachMonth[month - 1]);

et 

     return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;

car il y a beaucoup de conditions et d'opérations booléennes. Cependant, elles sont nécessaires.

Afin d'améliorer le score de mutation nous avons ajouté 2 TU pour la méthode **isLeapYear()** :

- **testIsLeapYearOK400()** 
- **testIsLeapYearNOK100()**

Cela a augmenté le score à 86%.


