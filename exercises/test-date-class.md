1 -

isValidDate :

| Characteristics | b1 | b2 | b3 | b4 | b5 |
|--|--|--|--|--|--|
|Year value| <0 |  0 | year%4=0(validLeapYear)| else(validCommonYear)|
|Month value| Month <= 0 | 1,3,5,7,8,10,12 (31 days Month) | 4,6,9,11 (30 days month) | 2 | >12|
|Day value| <=0 | >= 1 && <= max(month,year) | > max(month,year)

isLeapYear:

| Characteristics | b1 | b2 | b3 | b4 |
|--|--|--|--|--|
|Year value| <0 |  0 | year%4=0(validLeapYear)| else(validCommonYear)|

nextDate :
| Characteristics | b1 | b2 | b3 | b4 |
|--|--|--|--|--|
|Day value| <=0 | >= 1  && < max(month,year) | =max(month,year) |> max(month,year)|
|Month value| Month <= 0 | <12 | 12 | >12|
|Year value| <0 |  0 | year%4=0(validLeapYear)| else(validCommonYear)|

previousDate :
| Characteristics | b1 | b2 | b3 | b4 |
|--|--|--|--|--|
|Day value| <=0 | 1 | 1 && <= max(month,year) |> max(month,year)|
|Month value| Month <= 0 | 1 | > 1 && <= 12| > 12
|Year value| <0 |  0 | year%4=0(validLeapYear)| else(validCommonYear)|

compareTo :
| Characteristics | b1 | b2 | b3|
|--|--|--|--|
|year1 value| <year2 | =year2 | > year2|
|month1 value| year1==year2 &&  < month2 | year1==year2 &&  =month2 | year1==year2 &&  >month2|
|day1 value| year1 == year2 && month1==month2 && <day2 |  year1 == year2 && month1==month2 && =day2 | year1 == year2 && month1==month2 && >day2|

2 - With IntelliJ Idea the coverage of statements was 100%

4 - When we first runed the PIT analysis we got

```
- Statistics
================================================================================
>> Generated 8 mutations Killed 8 (100%)
>> Ran 10 tests (1.25 tests per mutation)

```

All the mutations were killed, so all the tests passed with every mutation.
As all the mutation are killed the coverage is good.