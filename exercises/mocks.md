# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

# Answer

Le code produit se trouve dans le fichier tp3-ssl. les tests ont été re-écris en utilisant moquito
Pour résoudre le problème où les tests ne détectaient pas l'absence de logique dans prepareSocket, ces nouvelles assertions avec Mockito valident que :

    La méthode setEnabledProtocols est bien appelée avec les bons paramètres.
    Tous les scénarios de combinaison de supported et enabled sont testés.

Avec cette approche, une modification ou suppression de la logique interne déclencherait un échec des tests. 