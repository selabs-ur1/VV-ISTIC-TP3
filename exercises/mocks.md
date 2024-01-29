# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we _entirely_ remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

# Answers

In these tests, Mockito.mock(SSLSocket.class) is used to create a mock object for the SSLSocket interface.

Then, the when method is used to define the behavior of the mock object for the getSupportedProtocols and getEnabledProtocols methods. 

Finally, the verify method is used to check whether the setEnabledProtocols method was called on the mock object with the expected arguments.
