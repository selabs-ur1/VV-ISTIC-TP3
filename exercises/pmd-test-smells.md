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

pmd ran with [bestpractices](../pmd-documentation/bestpractices.xml) on tests of the Apache Commons Collections.

Output can be found [here](./output.txt) (in this current folder).


Tests are clearly not clear and proper enough, here is some examples of their tests that would cause less PMD smells thanks to my modifications :

```shell
src\test\java\org\apache\commons\collections4\AbstractArrayListTest.java:44:	JUnitTestContainsTooManyAsserts:	Unit tests should not contain more than 1 assert(s).
```

```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.apache.commons.collections4.list.AbstractListTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract test class for ArrayList.
 */
public abstract class AbstractArrayListTest<E> extends AbstractListTest<E> {

    public AbstractArrayListTest(final String testName) {
        super(testName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract ArrayList<E> makeObject();

    // START OF OUR MODIFICATIONS
    @Test
    public void testNewArrayList() {
        final ArrayList<E> list = makeObject();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void testNewArrayListIsEmpty() {
        final ArrayList<E> list = makeObject();
        assertTrue(list.isEmpty(), "New list is empty");
    }

    @Test
    public void testNewArrayListLengthIsZero() {
        final ArrayList<E> list = makeObject();
        assertEquals(0, list.size(), "New list has size zero");
    }

    // END OF OUR MODIFICATIONS

    @Test
    @SuppressWarnings("unchecked")
    public void testSearch() {
        final ArrayList<E> list = makeObject();
        list.add((E) "First Item");
        list.add((E) "Last Item");
        assertEquals("First Item", list.get(0), "First item is 'First Item'");
        assertEquals("Last Item", list.get(1), "Last Item is 'Last Item'");
    }

}
```