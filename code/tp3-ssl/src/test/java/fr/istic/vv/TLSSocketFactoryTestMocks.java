package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;



public class TLSSocketFactoryTestMocks {


    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket sslSocket = mock(SSLSocket.class);
        when(sslSocket.getSupportedProtocols()).thenReturn(null);
        when(sslSocket.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(sslSocket);

        assertDoesNotThrow(() -> {
            verify(sslSocket, never()).setEnabledProtocols(any(String[].class));
        });
    }

    @Test public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket sslSocket = mock(SSLSocket.class);
        when(sslSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(sslSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        f.prepareSocket(sslSocket);

        ArgumentCaptor<String[]> captor = ArgumentCaptor.forClass(String[].class);
        verify(sslSocket).setEnabledProtocols(captor.capture());


        String[] capturedProtocols = captor.getValue();
        assertArrayEquals(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }, capturedProtocols);

    }
}