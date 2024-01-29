Yoann Dewilde  
Enora Danilo  
M2 ILa - Groupe 1  

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

Commande à utiliser : pmd check -d .\commons-collections\src\main\ -R .\VV-ISTIC-TP3\maven-pmd-plugin-default.xml --format=html -r out.html

1. Useless Assert : Il faut que chaque cas de test possède une assertion ou que celle-ci ne retourne pas toujours vrai (pour qu'il y ait réellement un test d'effectué)La règle de PMD *JUnitTestsShouldIncludeAssert* permet de vérifier qu'un test possède au moins une assertion.

**Exemple :**
```Java
@Test
    public void updateOneTestFromJira() throws ParseException {
        JiraIssuesJsonClient updater = new JiraIssuesJsonClient("http://gazelle.ihe.net/jira", "TES");
        updater.update("94");
    }
```
Il faut qu'un assert soit présent dans le cas de test, la méthode update retournant un String, il pourait donc il y avoir un asserEquals vérifiant que le bon String est retourné par la méthode update(). Si elle devait retourner "Update completed", cela donnerait le code suivant :

```Java
@Test
    public void updateOneTestFromJira() throws ParseException {
        JiraIssuesJsonClient updater = new JiraIssuesJsonClient("http://gazelle.ihe.net/jira", "TES");
        assertEquals("Update completed", updater.update("94"));
    }
```

2. Piggy Backing : Cet antipattern se produit lorsqu'une nouvelle fonctionnalité est ajoutée à une partie déjà existante du programme qui effectue déjà une autre fonctionnalité et qui n'était pas prévue pour en acceuillir une nouvelle.
La règle de PMD *ExcessiveMethodLength* permet de vérifier qu'une méthode n'est pas trop longue. En effet, lorsqu'une méthode est trop longue il se pourrait qu'elle implémente plusieurs fonctionnalités au lieu d'en implémenter une seule.

**Exemple :**
```Java
private Map<MultiKey, Authorization> computeAuthorizations() {
      Map<MultiKey, Authorization> result = new HashMap<MultiKey, Authorization>();

      result.put(new MultiKey(MASTER_MODEL, "view"), Authorizations.ALL);
      result.put(new MultiKey(MASTER_MODEL, "edit"), Authorizations.EDITOR);
      result.put(new MultiKey(ADDRESS_MANAGER, "addAddress"), Authorizations.ALL);
      result.put(new MultiKey(ADDRESS_MANAGER, "countryAutoComplete"), Authorizations.ALL);
      result.put(new MultiKey(ADDRESS_MANAGER, "getIso3166CountryCodes"), Authorizations.ALL);
      result.put(new MultiKey(ADDRESS_MANAGER, "updateAddress"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(ADDRESS_MANAGER, "deleteAddress"), Authorizations.ADMIN);
      result.put(new MultiKey(ADDRESS_MANAGER, "viewAddress"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(ADDRESS_MANAGER, "editAddress"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(ADDRESS_MANAGER, "createAddress"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(INSTITUTION_MANAGER, "addInstitution"), Authorizations.ALL);
      result.put(new MultiKey(INSTITUTION_MANAGER, "viewInstitution"), Authorizations.ALL);
      result.put(new MultiKey(INSTITUTION_MANAGER, "editInstitution"), Authorizations.ALL);
      result.put(new MultiKey(INSTITUTION_MANAGER, "validateInstitutionName"), Authorizations.ALL);
      result.put(new MultiKey(INSTITUTION_MANAGER, "validateInstitutionKeyword"), Authorizations.ALL);
      result.put(new MultiKey(INSTITUTION_MANAGER, "validateInstitutionAddress"), Authorizations.ALL);
      result.put(new MultiKey(INSTITUTION_MANAGER, "updateInstitution"), Authorizations.REGISTRATION_ADMIN);
      result.put(new MultiKey(INSTITUTION_MANAGER, "createFinancialInformationsForInstitution"),
            Authorizations.REGISTRATION_ADMIN);
      result.put(new MultiKey(INSTITUTION_MANAGER, "deleteInstitution"), Authorizations.ADMIN);
      result.put(new MultiKey(INSTITUTION_MANAGER, "addNewInstitutionButton"), Authorizations.ADD_NEW_INSTITUTION);
      result.put(new MultiKey(INSTITUTION_MANAGER, "editInstitutionSession"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(DOMAIN_MANAGER, "viewDomain"), Authorizations.ALL);

      result.put(new MultiKey(SYSTEM_MANAGER, "validateSystemNameAndSystemVersion"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_MANAGER, "validateSystemKeyword"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_MANAGER, "generateSystemKeyword"), Authorizations.REGISTRATION_EDITOR);

      result.put(new MultiKey(SYSTEM_IN_SESSION_CREATION, "addSystemForPR"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_CREATION, "addSystem"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_CREATION, "generateSystemKeyword"), Authorizations.REGISTRATION_EDITOR);

      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "addSystemForPR"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "editSystem"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "generateSystemKeyword"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "saveHL7URLInModalPanel"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "saveDicomURLInModalPanel"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "persistHL7DocumentsFiles"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "persistDicomDocumentsFiles"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "removeHL7URL"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "removeDicomURL"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "removeHL7Document"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "removeDicomDocument"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_EDTION, "updateSystemInSession"), Authorizations.REGISTRATION_EDITOR);

      result.put(new MultiKey(SYSTEM_IN_SESSION_LIST_MANAGER, "getSystemsInSessionListDependingInstitution"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_LIST_MANAGER, "deleteSystemInSession"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_LIST_MANAGER, "deleteSystemInAllSession"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_LIST_MANAGER, "createSystemSessionForSystem"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_LIST_MANAGER, "updateSelectedSystemInSession"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_IN_SESSION_LIST_MANAGER, "addInstitutionToSystem"),
            Authorizations.REGISTRATION_EDITOR);

      result.put(new MultiKey(USER_MANAGER, "addUser"), Authorizations.REGISTRATION_ADMIN);
      result.put(new MultiKey(USER_MANAGER, "setInstitution"), Authorizations.REGISTRATION_SUPER_ADMIN);
      result.put(new MultiKey(USER_MANAGER, "getInstitution"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(USER_MANAGER, "updateUser"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(USER_MANAGER, "deactivateUser"), Authorizations.REGISTRATION_ADMIN);
      result.put(new MultiKey(USER_MANAGER, "viewUser"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(USER_MANAGER, "editUser"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(USER_MANAGER, "createUser"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(USER_MANAGER, "getUsersListDependingInstitution"),
            Authorizations.PROJECT_MANAGER_OR_VENDOR_ADMIN);
      result.put(new MultiKey(USER_MANAGER, "getAllUsersListForAdmin"), Authorizations.PROJECT_MANAGER);

      result.put(new MultiKey(PERSON_MANAGER, "addNewContactButton"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "listContacts"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "addPerson"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "updatePerson"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "deleteContact"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "viewContact"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "editContact"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "updateContact"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "createPerson"), Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(PERSON_MANAGER, "createContact"), Authorizations.REGISTRATION_EDITOR);

      result.put(new MultiKey(DOMAIN_MANAGER, "addDomain"), Authorizations.EDITOR);
      result.put(new MultiKey(DOMAIN_MANAGER, "updateDomain"), Authorizations.EDITOR);
      result.put(new MultiKey(DOMAIN_MANAGER, "deleteDomain"), Authorizations.EDITOR);
      result.put(new MultiKey(DOMAIN_MANAGER, "editDomain"), Authorizations.EDITOR);
      result.put(new MultiKey(DOMAIN_MANAGER, "createDomain"), Authorizations.EDITOR);

      result.put(new MultiKey(SYSTEM_IN_SESSION_MANAGER, "canModifyTableSession"), Authorizations.ADMIN_OR_MONITOR);
      result.put(new MultiKey(DEMONSTRATION_MANAGER, "editDemonstration"), Authorizations.PROJECT_MANAGER);
      result.put(new MultiKey(DEMONSTRATION_MANAGER, "isDemonRegistered"),
            Authorizations.PROJECT_MANAGER_OR_VENDOR_ADMIN);
      result.put(new MultiKey(DEMONSTRATION_MANAGER, "setDemonstration"),
            Authorizations.PROJECT_MANAGER_OR_VENDOR_ADMIN);

      result.put(new MultiKey(TESTING_SESSION_MANAGER, "viewTestingSession"), Authorizations.ALL);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "addTestingSession"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "updateTestingSession"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER_OR_TESTING_SESSION_ADMIN_OF_CURRENT_TESTING_SESSION);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "deleteTestingSession"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "editTestingSession"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER_OR_TESTING_SESSION_ADMIN_OF_CURRENT_TESTING_SESSION);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "createTestingSession"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "addNewTestingSessionButton"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER);
      result.put(new MultiKey(TESTING_SESSION_MANAGER, "activateSession"), Authorizations
            .ADMIN_OR_PROJECT_MANAGER_OR_TESTING_SESSION_ADMIN_OF_CURRENT_TESTING_SESSION);

      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "getSyslogConfigurationListDependingSystemInSession"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "generateConfigurationsForCompany"), Authorizations.ADMIN);
      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "generateConfigurationsForSystem"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "generateAllConfigurationsForSession"),
            Authorizations.ADMIN);
      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "deleteConfiguration"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "addEditConfiguration"),
            Authorizations.REGISTRATION_EDITOR);
      result.put(new MultiKey(SYSTEM_CONFIGURATION_MANAGER, "deleteAllConfigurationsForSystem"),
            Authorizations.ADMIN_OR_VENDOR);
      result.put(new MultiKey("ConnectathonResultManager", "manageSystemAIPOResultsForAdmin"),
            Authorizations.ADMIN_OR_PROJECT_MANAGER_OR_TESTING_SESSION_ADMIN_OF_CURRENT_TESTING_SESSION);
      result.put(new MultiKey("ConnectathonResultManager", "manageSystemAIPOResultsForNonAdmin"),
            Authorizations.MONITOR_OR_VENDOR_OR_VENDOR_ADMIN);
      result.put(new MultiKey(TESTING_MANAGER, "manageInstanceOfTestForAdmin"), Authorizations.ADMIN_OR_MONITOR);
      result.put(new MultiKey(TESTING_MANAGER, "listOfPreconnectathonTestsForAdmin"), Authorizations.ADMIN_OR_MONITOR);
      result.put(new MultiKey(TESTING_MANAGER, "listOfPreconnectathonTestsForNonAdmin"), Authorizations.VENDOR);
      result.put(new MultiKey(TESTING_MANAGER, "findSystemsInSessionForTestingAsAdmin"),
            Authorizations.ADMIN_OR_MONITOR);
      result.put(new MultiKey(TESTING_MANAGER, "findSystemsInSessionForTestingAsNonAdmin"), Authorizations.VENDOR);
      result.put(new MultiKey(TESTS_DEFINITIONS_ADMINISTRATION_MANAGER, "EditTest"), Authorizations.EDITOR);
      result.put(new MultiKey(TESTS_DEFINITIONS_ADMINISTRATION_MANAGER, "ReadTest"), Authorizations.LOGGED);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfObjectCreatorForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfObjectReaderForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfObjectFileForSelectedObjectTypeForCreator"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfObjectFileForSelectedObjectTypeForReaders"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfObjectAttributeForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "addNewCreatorOfcurrentObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "addNewReaderOfcurrentObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getPossibleFileTypes"), Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "addNewObjectFileTypeToObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "addNewObjectAttributeToObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "updateSelectedObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getNumberOfObjectInstanceByObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getNumberOfObjectInstanceBySISForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListSISofSelectedObjectTypeForCreation"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getTableOfSIS"), Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfAIPOCreatorImplementedBySISForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListSISofSelectedObjectTypeForReading"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListOfAIPOReaderImplementedBySISForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "mergeSelectedObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "updateSelectedCreatorOfcurrentObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "updateSelectedReaderOfcurrentObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "mergeObjectFileTypeOfObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "mergeObjectAttributeOfObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListObjectInstanceForObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "viewListOIFOnDeleting"), Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "deleteSelectedObjectTypeFromDataBaseForSelectedObjectType"),
            Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "deleteSelectedCreatorFromDataBase"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "deleteSelectedReaderFromDataBase"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "deleteSelectedObjectFileFromDataBase"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "deleteSelectedObjectAttributeFromDataBase"),
            Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "mergeObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "getListObjectInstanceForSelectedObjectType"),
            Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(OBJECT_TYPE_MANAGER, "editObjectType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_FILE_TYPE_MANAGER, "mergeSelectedObjectFileType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_FILE_TYPE_MANAGER, "generateMessageForDeleteObjectFileType"),
            Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_FILE_TYPE_MANAGER, "deleteSelectedObjectFileType"), Authorizations.EDITOR);
      result.put(new MultiKey(OBJECT_FILE_TYPE_MANAGER, "persistObjectFileType"), Authorizations.EDITOR);
      result.put(new MultiKey(ANNOTATION_MANAGER, "getListObjectInstanceAnnotation"), Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(ANNOTATION_MANAGER, "deleteSelectedAnnotation"), Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(ANNOTATION_MANAGER, "getSamplePermanentlink"), Authorizations.OBJECT_VIEWER);
      result.put(new MultiKey(INVOICE_ADMIN_MANAGER, "saveInvoice"),
            Authorizations.ACCOUNTING_OR_TESTING_SESSION_ADMIN_OF_CURRENT_TESTING_SESSION);
      return result;
   }
```
Cette méthode est extrêmement longue et pourrait être sépérarée en plusieurs sous méthode qui regroupe les différents types d'actions.

3. Happy Path : Cet antipattern se produit lorsque le code est conçu seulement en prévoyant que tout fonctionne correctement, c'est-à-dire sans tenir compte des possibles erreurs qui pourraient survenir, comme des exceptions par exemple.
La règle de PMD *PreserveStackTrace* permet de détecter les blocs catch qui attrapent une exception mais ne font rien pour la préserver ou la loguer. Dans le cas de l'Happy path cela pourrait nous indiquer qu'une exception n'est pas traitée.

**Exemple :**
```java
    try {
        return Array.get(object, i);
    } catch (final IllegalArgumentException ex) {
        throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
    }

```

4. Circular Dependency : Cet antipattern se produit lorsque différentes parties du code possèdent des dépendances circulaires, ce qui peut rendre le code difficile à organiser et à tester.
La règle de PMD *CyclomaticComplexity* permet de détecter lorsque les méthodes ayant une complexité supérieur ou égal à 10.

**Exemple :**
```Java
 public static Object get(final Object object, final int index) {
        final int i = index;
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + i);
        }
        if (object instanceof Map<?, ?>) {
            final Map<?, ?> map = (Map<?, ?>) object;
            final Iterator<?> iterator = map.entrySet().iterator();
            return IteratorUtils.get(iterator, i);
        }
        if (object instanceof Object[]) {
            return ((Object[]) object)[i];
        }
        if (object instanceof Iterator<?>) {
            final Iterator<?> it = (Iterator<?>) object;
            return IteratorUtils.get(it, i);
        }
        if (object instanceof Iterable<?>) {
            final Iterable<?> iterable = (Iterable<?>) object;
            return IterableUtils.get(iterable, i);
        }
        if (object instanceof Enumeration<?>) {
            final Enumeration<?> it = (Enumeration<?>) object;
            return EnumerationUtils.get(it, i);
        }
        if (object == null) {
            throw new IllegalArgumentException("Unsupported object type: null");
        }
        try {
            return Array.get(object, i);
        } catch (final IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
        }
    }
```

Ici PMD retourne le meassage suivant : "The method 'get(Object, int)' has a cyclomatic complexity of 12."

5. Ce n'est pas un antipattern mais nous avons vu en cours qu'une méthode de test annotée @Test ne doit possèder qu'une seule assertion. Nous pouvons donc vérfier cela avec la règle de PMD *JUnitTestContainsTooManyAsserts*.

**Exemple :**
```Java
    @Test
    public void testNewArrayList() {
        final ArrayList<E> list = makeObject();
        assertTrue(list.isEmpty(), "New list is empty");
        assertEquals(0, list.size(), "New list has size zero");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
```

Ici il faudrait donc que les différents tests soient séparés de cette façon :

```java
    @Test
    public void testNewArrayList1() {
        final ArrayList<E> list = makeObject();
        assertTrue(list.isEmpty(), "New list is empty");
    }

    @Test
    public void testNewArrayList2() {
        final ArrayList<E> list = makeObject();
        assertEquals(0, list.size(), "New list has size zero");
    }

    @Test
    public void testNewArrayListEmptyException() {
        final ArrayList<E> list = makeObject();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
```
