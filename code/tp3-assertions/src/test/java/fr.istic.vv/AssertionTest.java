package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class ObjectTest {
    private int i;
    public ObjectTest(int i)
    {
        this.i = i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o ){
            return true;
        }
        if( o == null ) {
            return false;
        }
        if( getClass() != o.getClass() ) {
            return false;
        } else {
            ObjectTest objectTest = (ObjectTest) o;
            return Objects.equals(objectTest, objectTest);
        }
    }
}



class AssertionTest {

    @Test
    void testAssertions1Right() {
        Assertions.assertEquals(1.2, Math.round(3 * .4 * 10.0) / 10.0);
    }

    @Test
    void testAssertions1Failed() {
        //it is always going to be false
        Assertions.fail("3 * 4  is equal to 1.2000000000000002. It always going to be false");
        Assertions.assertTrue(3*.4==1.2);

    }

    @Test
    void testAssertions2Equals() {
        ObjectTest a = new ObjectTest(1);
        ObjectTest b = new ObjectTest(1);

        //is correct
        Assertions.assertEquals(a,b);
    }

    @Test
    void testAssertions2SameFailed() {
        ObjectTest a = new ObjectTest(1);
        ObjectTest b = new ObjectTest(1);

        //should fail
        Assertions.fail("Assert Same does not work with two different object instances");
        Assertions.assertSame(a, b);
    }

    @Test
    void testAssertSameSameInstanceDifferentValue() {
        ObjectTest a = new ObjectTest(1);
        ObjectTest b = a;
        b.setI(2);

        //should fail
        Assertions.assertSame(a,b);
    }

}