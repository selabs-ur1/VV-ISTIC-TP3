# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. L'assertion n'est pas satisfaite car l'opération effectue une aproximation du résultat. A la place d'un AssertTrue, il serait possible de faire un AssertEquals avec un petit delta.
2. Avec AssertSame, l'oracle tente de vérifier si les deux instances d'objets sont les mêmes. Avec AssertEquels, l'oracle vérifie seulement les valeurs de l'objet.
````java
Rectangle r1  = new Rectangle(2,5);
Rectangle r2  = new Rectangle(2,5);

@Test
void TestRectangleSameFalse() {
    assertSame(r1, r2); // return false
}

@Test
void TestRectangleSameTrue() {
    assertSame(r1, r1); // return true
}

@Test
void TestRectangleEqualsTrue() {
    assertEquals(r1, r2); // return true
}
        
@Test
void TestRectangleArea() {
    assertEquals(r1.area(), r2.area()); // return true car les rectangles ont la même aire
        }

````
3. fail permet de renforcer la compréhension de l'utilisateur en lui donnant des "aides" d'utilisation/compréhension du programme
````java
public Rectangle (height, width){
    if(!(height>0 | width>0)){
        fail("Value seems incorrect. Please make sure that you provide values > 0.");
        }
    if(height == width){
        fail("You should use the class Square instaead.");        
        }
    else{
        this.height = height; 
        this.width = width;
        }
}
````
4. assertThrows permet de renforcer la qualité des tests en s'assurant que le test "fail" effectivement comme on l'attendait.