package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    /*
    Pour palier au souci soulevé dans l'énoncé, on utilise verify qui s'assure que les appels aux méthodes ont bien lieux.
    Aussi l'utilisation d'ArgumentCaptor permet de s'assurer que les arguments utilisés lors des appels sont bien ceux qu'on attend.
     */

    @Test
    public void testPrepareSocket_NullProtocols() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(mockSocket);

        verify(mockSocket, never()).setEnabledProtocols(any());
    }

    @Test
    public void testPrepareSocket_TypicalCase() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols())
                .thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols())
                .thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        ArgumentCaptor<String[]> captor = ArgumentCaptor.forClass(String[].class);

        factory.prepareSocket(mockSocket);

        verify(mockSocket).setEnabledProtocols(captor.capture());
        assertArrayEquals(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"}, captor.getValue());
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
