# Mocks - Ilan HUCHÉ - Aiman AOUAD

La classe TLSSocketFactory propose une méthode prepareSocket pour configurer un objet SSLSocket en définissant ses protocoles activés en fonction des protocoles supportés et déjà activés.

Pour garantir la fiabilité de cette méthode, des tests ont été élaborés dans la classe TLSSocketFactoryTestMocks en utilisant Mockito. 
Ces tests, couvrant des scénarios tels que des protocoles supportés ou activés nuls, ainsi qu'un cas typique avec des valeurs spécifiques, offrent une validation complète de la logique de la méthode prepareSocket. 

En utilisant Mockito pour créer des simulacres d'objets SSLSocket et définir les comportements attendus de ces simulacres, les déclarations verify assurent que la méthode setEnabledProtocols est appelée avec les arguments prévus. 

Cette approche de tests, intégrant Mockito pour la simulation et le bouchonnage, renforce la fiabilité des cas de test en isolant le comportement de la méthode prepareSocket et en permettant des assertions ciblées sur ses interactions avec les objets SSLSocket. 

Dans l'ensemble, cette stratégie de test contribue à la qualité et à la justesse globales de l'implémentation de la classe TLSSocketFactory.

See code in test folder. ([`TLSSocketFactoryTestMocks`](../code/tp3-ssl/src/test/java/fr/istic/vv/TLSSocketFactoryTestMocks.java))