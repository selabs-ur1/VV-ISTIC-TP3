package fr.istic.vv;

import java.util.List;
import java.util.Random;

public class DateTestUtils {

    public static final List<Integer> integer = List.of(0,1,2,3,4,5,6,7,8,9);


    public static int createMonth()
    {
        Random r = new Random();
        int random = r.nextInt(10);
        final int unit = integer.get(random % 10);
        random = r.nextInt(10);
        final int decimal = integer.get(random % 3) * 10;
        return unit + decimal;
    }

    public static int createDay()
    {
        Random r = new Random();
        int random = r.nextInt(10);
        final int unit = integer.get(random % 10);
        random = r.nextInt(10);
        final int decimal = integer.get(random % 10) * 10;
        return unit + decimal;
    }

    public static int createYear()
    {
        Random r = new Random();
        int random = r.nextInt(10);

        int result = integer.get(random % 10);

        random = r.nextInt(10);
        result = result + integer.get(random % 10) * 10;

        random = r.nextInt(10);
        result = result + integer.get(random % 10) * 100;

        random = r.nextInt(10);
        result = result + integer.get(random % 10) * 100;
        return result;
    }
}
