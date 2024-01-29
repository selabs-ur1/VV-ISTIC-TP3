Yoann Dewilde  
Enora Danilo  
M2 ILa - Groupe 1  

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

### isDateValid :
Il faut que l'année soit un entier positif et que :

| Jour | Février | Janvier, Mars, Mai, Juillet, Aout, Octobre, Décembre | Avril, Juin, Septembre, Novembre |
|:--------:|:-------:|:--------:|:--------:|
| < 1 ou > 31 | x | x | x |
| > 1 | v | v | v |
| = 28 | v | v | v |
| = 29 | si année bissextile | v | v |
| = 30 | x | v | v |
| = 31 | x | v | x |

### isLeapYear :
| Année | Bissextile |
|:--------:|:-------:|
|(year % 4 == 0 ∩ year % 100 != 0) ∪ (year % 400 == 0) | v |
| sinon | x |

### getNextDay :
Toutes les caractéristiques de isDateValid sont valables ici.

| Cas particuliers | entrée | sortie |
| :-----------: | :-----------: | :-----------: | 
| Dernier jour du mois | 31/01/2024 | 01/02/2024 |
| Dernier jour du mois de février année bissextile | 28/02/2024 | 29/02/2024 |
| Dernier jour du mois de février année non bissextile | 28/02/2023 | 01/03/2023 |
| Dernier jour du mois de l'année | 31/12/2023 | 01/01/2024 |
| Jour suivant | 05/08/2023 | 06/08/2024 |

### getPreviousDay :
Toutes les caractéristiques de isDateValid sont valables ici.

| Cas particuliers | entrée | sortie |
| :-----------: | :-----------: | :-----------: | 
| Premier jour du mois | 01/02/2024 | 31/01/2024 |
| Premier jour du mois de février année bissextile | 01/03/2024 | 29/02/2024 |
| Premier jour du mois de février année non bissextile | 01/03/2023 | 28/02/2023 |
| Premier jour du mois de l'année | 01/01/2024 | 31/12/2023 |
| Jour précédent | 06/08/2024 | 05/08/2023 |

### compareTo :
Toutes les caractéristiques de isDateValid sont valables ici.

| d1.compareTo(d2) | sortie |
| :-----------: | :-----------: | 
| d2 == null | nullPointerException |
| d1 == d2 | 0 |
| d1.year < d2.year | -1 |
| d1.month < d2.month (et même année) | -1 | 
| d1.day < d2.day (et même année et même mois) | -1 |
| d1.year > d2.year | 1 |
| d1.month > d2.month (et même année) | 1 | 
| d1.day > d2.day (et même année et même mois) | 1 |

2. et 3. Voici la couverture des tests que nous avons décrit précédement.
![Alt text](image-5.png)

Pour ce faire, nous avons écrit nos tests avec Junit puis utilisé **Run 'la classe de test' with Coverage**. 
Nous pouvons voir que certaines lignes ne sont pas testées. En effet, les cas d'une année invalide, d'un mois invalide et d'une inégalité de dates ne sont pas testés.

![Alt text](image-2.png)
![Alt text](image-3.png)
![Alt text](image-4.png)

Après avoir ajouté les tests nécéssaires, toutes les lignes du code sont testées. Nous avons également ajouté les tests permettant de satisfaire le *Base Choice Coverage*.
![Alt text](image-7.png)

4. 
![Alt text](image-6.png)
