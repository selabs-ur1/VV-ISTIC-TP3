# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

## Answer

Afin de s’assurer qu’aucun test n’échoue lorsqu’on retire tout le code de la classe `prepareSocket`, il peut être intéressant de rajouter des méthodes `verify`.
Il s'agit d’une méthode proposée par Mockito afin de savoir combien de fois une méthode a été invoquée dans le test. Ainsi, nous pouvons nous assurer que les méthodes sont forcément appelées, et si oui s'assurer du nombre de fois où elles le sont.
