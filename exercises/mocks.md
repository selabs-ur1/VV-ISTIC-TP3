# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

## Answers
To make a test stronger, Mockito has a `verifiy()` method that will make sure the given method of an object is called at least once 
or exactly a number of `times()` given. This requires to know the body of the tested method  but ensures a better verification and can be used
to trace the execution path exactly.
