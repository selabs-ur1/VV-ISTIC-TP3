# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

## Answer

J'ai mis en place les deux tests avec mockito. Je n'utilise plus la fonction shuffle qui rend les tests plus difficile à débugger en cas d'echec. Cela permet d'éviter une possible apparition de flaky test.

Les tests ne font pas d'echec si on enleve le prepareSocket car on n'a pas d'assertions sur le comportement exact dans prepareSocket et comment il est appelé exactement, avec quels protocoles. On ne vérifiait pas les interactions avec les données mockés. On ajoute donc des verify pour vérifier que prepareSocket appel bien les méthodes mockés et que setEnabled et appelés avec les bons protocoles.

```java
    verify(mockSocket).getSupportedProtocols();
    verify(mockSocket).getEnabledProtocols();
```
