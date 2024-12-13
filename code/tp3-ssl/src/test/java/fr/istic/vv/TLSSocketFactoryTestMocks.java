package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        // preparation
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        // action
        factory.prepareSocket(mockSocket);

        // oracle
        verify(mockSocket).getSupportedProtocols();
        verify(mockSocket).getEnabledProtocols();
        verify(mockSocket, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical() {
        // preparation
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        String[] supportedProtocols = { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" };
        String[] enabledProtocols = { "SSLv3", "TLSv1" };
        String[] expectedProtocols = { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" };

        when(mockSocket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(mockSocket.getEnabledProtocols()).thenReturn(enabledProtocols);

        // action

        factory.prepareSocket(mockSocket);

        // oracle
        verify(mockSocket).getSupportedProtocols();
        verify(mockSocket).getEnabledProtocols();
        verify(mockSocket).setEnabledProtocols(expectedProtocols);
    }
}