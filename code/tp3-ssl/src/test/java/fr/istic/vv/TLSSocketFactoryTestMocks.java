package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TLSSocketFactoryTestMocks {
    @Test
    void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();

        // Créer un mock de SSLSocket et définie le retour des méthodes
        SSLSocket mockedSocket = mock(SSLSocket.class);

        when(mockedSocket.getSupportedProtocols()).thenReturn(null);
        when(mockedSocket.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(mockedSocket);

        //On vérifie que getSupportedProtocols() et getEnabledProtocols() sont appelées dans le bon ordre
        InOrder inOrder = inOrder(mockedSocket);
        inOrder.verify(mockedSocket).getSupportedProtocols();
        inOrder.verify(mockedSocket).getEnabledProtocols();

        // On vérifie que setEnabledProtocols n'est jamais appelé
        verify(mockedSocket, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        // Créer un mock de SSLSocket et défini le retour des méthodes
        SSLSocket mockedSocket = mock(SSLSocket.class);

        when(mockedSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockedSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        f.prepareSocket(mockedSocket);

        //On vérifie que getSupportedProtocols() et getEnabledProtocols() sont appelées dans le bon ordre
        InOrder inOrder = inOrder(mockedSocket);
        inOrder.verify(mockedSocket).getSupportedProtocols();
        inOrder.verify(mockedSocket).getEnabledProtocols();

        // On vérifie que setEnabledProtocols retourne le bon résultat
        inOrder.verify(mockedSocket).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
