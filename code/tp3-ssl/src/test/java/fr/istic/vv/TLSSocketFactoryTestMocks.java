package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory tlSSocketFactory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);
        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        tlSSocketFactory.prepareSocket(mockSocket);
        verify(mockSocket, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    public void typical() {
        TLSSocketFactory tlSSocketFactory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);
        when(mockSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        tlSSocketFactory.prepareSocket(mockSocket);
        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }
}
