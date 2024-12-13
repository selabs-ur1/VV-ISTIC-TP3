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

1. Input Space Partitioning

- Date(int day, int month, int year)
  - Caractéristiques :
    - Jour valide (1, 31)
    - Mois valide (1, 12)
    - Année valide (1900, 2100)
    - Jour invalide (0, 32)
    - Mois invalide (0, 13)
    - Année invalide (1899, 2101)
  - Caractéristiques communes :
    - Jour, mois et année apparaissent dans isValidDate, nextDate et previousDate

- isValidDate(int day, int month, int year)
  - Caractéristiques :
    - Année bissextile ou non
    - Cas limites sur les mois :
      - Mois à 31 jours (Janvier, Mars, etc)
      - Mois à 30 jours (Avril, Juin, etc)
      - Février avec et sand date valide (1-28, 29 si année bissextile)
  - Caractéristiques communes :
    - Les cas limites peuvent être appliqués à isLeapYear, nextDate et previousDate

- isLeapYear(int year)
  - Caractéristiques :
    - Divisible par 4 mais pas par 100 (2024, 2004)
    - Divisible par 400 (2000, 1600)
    - Divisible par 100 mais pas par 400 (1900, 2100)
    - Non divisible par 4 (2023, 2019)
  - Caractéristiques communes :
    - isValidDate la même logique

- nextDate()
  - Caractéristiques :
    - Jour normal (15)
    - Dernier jour d'un mois transitant vers le premier jour du mois suivant
    - Dernier jour de février transitant vers le premier jour de mars pour une année bissextile et non bissextile
    - Dernier jour de l'année transitant vers le premier jour de l'année suivante
  - Caractéristiques communes :
    - isValidDate la même logique

- previousDate()
  - Caractéristiques :
    - Jour normal (15)
    - Premier jour d'un mois transitant vers le dernier jour du mois précédent
    - Premier jour de l'année transitant vers le dernier jour de l'année précédente
    - 1er mars transitant vers le dernier jour de février pour une année bissextile et non bissextile (29 ou 28)
  - Caractéristiques communes :
    - Comportement similaire à nextDate mais en sens inverse

- compareTo(Date other)
  - Caractéristiques :
    - Date identique
    - Date antérieure
    - Date postérieure
    - Valeur limite en comparant le dernier jour de l'année/ du mois à celui du premier jour de l'année/mois suivante
    - Valeur invalide ou null

2. Statement coverage
Après avoir écrit les tests pour les caractéristiques identifiées, j'ai évalué la couverture de chaque méthode avec JaCoCo. J'ai obtenu la couverture suivante :

| Method | Instructions coverage | Branch coverage |
|--------|-----------------------|-----------------|
| getDay | 0% | 0% |
| getMonth | 0% | 0% |
| getYear | 0% | 0% |
| compareTo | 68% | 66% |
| getDaysInMonth | 86% | 83% |
| nextDate | 100% | 100% |
| previousDate | 100% | 100% |
| isValidDate | 100% | 100% |
| isLeapYear | 100% | 100% |
| Date | 100% | 100% |

Les méthodes `getDay`, `getMonth` et `getYear` ne sont pas testées car elles ne sont pas utilisées dans les tests.

J'ai ajouté les tests manquant pour compareTo, puis j'ai passé getDaysInMonth en public afin de pouvoir le tester puis j'ai ajouté des tests pour cette méthode.

J'ai maintenant une couverture de 100% pour toutes les méthodes.

3. Base Choice Coverage

Les méthodes `isLeapYear` et `isValidDate` utilisent des prédicats avec plus de deux opérateurs booléens.
Voici l'analyse de ces méthodes :

- `isValidDate` :
- Prédicat 1 : `month < 1 || month > 12`
  - Vrai : `month = 0`
  - Faux : `month = 6`

- Prédicat 2 : `day >= 1 && day <= daysInMonth`
  - Vrai : `day = 15, month = 6, year = 2024`
  - Faux : `day = 32, month = 6, year = 2024`

  - Les cas de tests éxistent déjà :
    - isValidDate(0, 6, 2024) → False (month < 1)
    - isValidDate(15, 6, 2024) → True (month valid, day valid)
    - isValidDate(32, 6, 2024) → False (month valid, day invalid)
  
- `isLeapYear` :
- Prédicat 1 : `year % 4 == 0`
  - Vrai : `year = 2024`
  - Faux : `year = 2023`
- Prédicat 2 : `year % 100 == 0`
  - Vrai : `year = 1900`
  - Faux : `year = 2024`
- Prédicat 3 : `year % 400 == 0`
  - Vrai : `year = 2000`
  - Faux : `year = 1900`

- Les tests suivants existent déjà :
  - isLeapYear(2023) → False (year % 4 != 0)
  - isLeapYear(2024) → True (year % 4 == 0, year % 100 != 0)
  - isLeapYear(1900) → False (year % 4 == 0, year % 100 == 0, year % 400 != 0)
  - isLeapYear(2000) → True (year % 4 == 0, year % 100 == 0, year % 400 == 0)

4. Mutation testing

J'ai utilisé PIT pour évaluer la suite de tests. J'ai obtenu un score de mutation de 92% avec 49 mutants tués sur 53. Les mutants restants ne sont pas pertinents pour ce code car ils concernent des cas de tests qui ne sont pas applicables à ce code.