# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.


# Answer

We were not sure what to test in the `preparedSocket_NullProtocolsTest`. 

In fact if I comment all the code inside the `prepareSocket` method my tests are still valid. I propose a solution that will use an attribute
in the TLSSocketFactory class that is equivalent to target variable inside the code.

Make sure to set the list to empty if necessary before using this method, or you may have problems. 

We think if we modify some values inside a method or return something, now we could test things.
