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
### 1. Input Space : 

#### isValidDate:

| Partition  | Description                                     | Caractéristiques et Blocages                      |
|------------|-------------------------------------------------|---------------------------------------------------|
| isValide: (true)   | Jour valide, Mois valide, Année valide            | Aucun blocage identifié                            |
| isValide: (false)  | Jour invalide (> 31), Jour invalide (< 0)        | Caractéristiques communes :                       |
|                  | Mois invalide (> 12), Mois invalide (<= 0)       | - Les mois sont invalides s'ils sont hors limites |
|                  | Année valide, Année invalide (< 0)               | - Les années négatives sont invalides (sauf si gérées par LocalDate) |


#### isLeapYear:

| Partition              | Description                                | Caractéristiques et Blocages                       |
|------------------------|--------------------------------------------|----------------------------------------------------|
| Année bissextile       | Année divisible par 4 et non divisible par 100, ou divisible par 400 | Aucun blocage identifié                             |
| Année non bissextile   | Année non divisible par 4, ou divisible par 100 mais non par 400 | Aucun blocage identifié                             |
| Année 0                | Année égale à 0                             | Aucun blocage identifié                             |
| Année négative         | Année inférieure à 0                        | Dépend de la gestion par LocalDate (notamment si les années négatives sont valides) |


#### compareTo:

| Partition   | Description                          | Caractéristiques et Blocages                       |
|-------------|--------------------------------------|----------------------------------------------------|
| Date null   | Date en entrée est nulle              | Aucun blocage identifié                             |
| Date ==     | Dates en entrée sont égales           | Aucun blocage identifié                             |
| Date before | Date en entrée est antérieure         | Aucun blocage identifié                             |
| Date next   | Date en entrée est ultérieure         | Aucun blocage identifié                             |


#### nextDate: 
pas de param en entré
#### previousDate: 
pas de param en entré



### 2. Test Coverage :

J'ai testé le coverage avec intellij et j'obtiens les résultats suivants :

| Class     | Method         | Line         |
|-----------|----------------|--------------|
| 100% (1/1)| 100% (7/7)     | 100% (26/26) |


### 3. Base Choice Coverage 

Lors de la prise en compte des cas test et leur écriture j'ai pris en compte le *Base Choice Coverage* pour les écrire.

### 4. PIT:
code exec:
```shell
mvn clean test org.pitest:pitest-maven:mutationCoverage
```

PIT me retourne le résultat suivant :

| Name         | Number of Classes | Line Coverage | Mutation Coverage |
|--------------|-------------------|---------------|-------------------|
| fr.istic.vv  | 1                 | 100% (27/27)   | 89% (19/21)      |

