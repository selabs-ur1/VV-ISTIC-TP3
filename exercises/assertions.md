# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Les nombres à virgules flottante, de part leur representation binaire (signe,exposant,mantisse) sont approximés.  
Ainsi la répresentation de 3\*0.4 sera une approximation de (3\*0.4) mais ne vaudra pas exactement 1.2.  
La réprésentation binaire entraine des sortes de residus, ainsi 3\*0.4 pourrait valoir quelques chose comme 1.200000001.  
Pour palier à ceci, il faut preciser un degré de precision dans notre test.  
Ici une precision à 0.1 suffit -> assertEquals(1.2, 3 \* .4, 0.1);  

2. assertEquals sur deux objet ira utilisera la methode equals qui devrait être rédefinie par ces objets.
assertSame compare des référence, cela équivaut au comparateur "==".

Scenario où assertEquals <=> assertSame : 
```
public class A {
	private int attr;
	
	public A() {}
	public A(int i) {
		this.attr = i;
	}
	public int getAttr() {
		return attr;
	}
	public void setAttr(int attr) {
		this.attr = attr;
	}
}

//Dans une classe de test

	@Test
	public void scenarioAEquals() {
		A a1 = new A(1);
		A a2 = new A(1);
		assertEquals(a1,a2);
	}
	
	@Test
	public void scenarioASame() {
		A a1 = new A(1);
		A a2 = new A(1);
		assertSame(a1,a2);
	}
```

Scénario où assertEquals != assertSame
```
public class A2 {
	private int attr;
	
	public A2(int i) {
		this.attr = i;
	}

	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!(obj instanceof A2))
			return false;
		A2 casted = (A2)obj;
		if(casted.getAttr() != this.attr)
			return false;
		return true;
	}
}

//Dans une classe de test
	@Test
	public void scenarioA2Equals() {
		A2 a1 = new A2(1);
		A2 a2 = new A2(1);
		assertEquals(a1,a2);
	}
	
	@Test
	public void scenarioA2Same() {
		A2 a1 = new A2(1);
		A2 a2 = new A2(1);
		assertSame(a1,a2);
	}
```


3. Fail pourrait être utiliser pour detecter une incohérence lors de la phase d'initialisation d'un scenario de test.
L'incohérence seule aurait amené le test à echouer mais pour la mauvaise raison.
En ajoutant fail, on echoue délibérément le test en signalant que cela ne provient pas de l'oracle.

par exemple : 
```
public void exempleFail(){
	ObjetX obj = ObjetXFabric.getOneInstance();

	if(obj == null)
		fail("erreur lors de l'initialisation");
	//Suite d'un scenario classique se terminant par un oracle
}
```

4. L'utilisation de assertThrows offre un bien meilleur controle :
- On peut spécifier le type d'exception attendu.
- On peut capturer l'exception pour y effectuer des verifications
- Dans une méthode de test, c'est plus clair, on "marque" plus précisement l'endroit ou l'exception peut se produire.

