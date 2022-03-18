package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {


    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        //init
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket sslSocket = mock(SSLSocket.class);

        when(sslSocket.getSupportedProtocols()).thenReturn(null);
        when(sslSocket.getEnabledProtocols()).thenReturn(null);

        // Appel de la méthode
        f.prepareSocket(sslSocket);

        //Vérification de l'exécution des méthodes prévues
        verify(sslSocket, times(1)).getSupportedProtocols();
        verify(sslSocket, times(1)).getEnabledProtocols();
    }

    @Test
    public void typical() {
        //init
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket sslSocket = mock(SSLSocket.class);

        when(sslSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(sslSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        // Appel de la méthode
        f.prepareSocket(sslSocket);

        //Vérification de l'exécution des méthodes prévues
        verify(sslSocket, times(1)).getSupportedProtocols();
        verify(sslSocket, times(1)).getEnabledProtocols();

    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}