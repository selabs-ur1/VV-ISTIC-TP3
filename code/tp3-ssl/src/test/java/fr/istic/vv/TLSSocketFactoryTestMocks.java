package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {
    // Mockito mocks for SSLSocket
    private SSLSocket mockSSLSocket(String[] supported, String[] enabled) {
        SSLSocket mock = Mockito.mock(SSLSocket.class);
        Mockito.when(mock.getSupportedProtocols()).thenReturn(supported);
        Mockito.when(mock.getEnabledProtocols()).thenReturn(enabled);
        return mock;
    }

    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSSLSocket = mockSSLSocket(null, null);
        f.prepareSocket(mockSSLSocket);
        assertNull(mockSSLSocket.getEnabledProtocols());
        assertNull(mockSSLSocket.getSupportedProtocols());
    }

    @Test
    public void preparedSocket_NullSupported() {
        TLSSocketFactory f = new TLSSocketFactory();
        String[] enabled = new String[] {"SSLv3", "TLSv1"};
        SSLSocket mockSSLSocket = mockSSLSocket(null, enabled);
        f.prepareSocket(mockSSLSocket);
        assertEquals(mockSSLSocket.getEnabledProtocols(), enabled);
        assertNull(mockSSLSocket.getSupportedProtocols());
    }

    @Test
    public void preparedSocket_NullEnabled() {
        TLSSocketFactory f = new TLSSocketFactory();
        String[] supported = new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        SSLSocket mockSSLSocket = mockSSLSocket(supported, null);
        f.prepareSocket(mockSSLSocket);
        assertNull(mockSSLSocket.getEnabledProtocols());
        assertEquals(mockSSLSocket.getSupportedProtocols(), supported);
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        String[] supported = new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        String[] enabled = new String[] {"SSLv3", "TLSv1"};
        SSLSocket mockSSLSocket = mockSSLSocket(supported, enabled);
        f.prepareSocket(mockSSLSocket);
        assertEquals(mockSSLSocket.getEnabledProtocols(), enabled);
        assertEquals(mockSSLSocket.getSupportedProtocols(), supported);
    }
}