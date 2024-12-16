package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    @Test
    void shouldSupportAndEnableWhenPrepareSocketGivenNullProtocols() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(mockSocket);

        verify(mockSocket, never()).setEnabledProtocols(any()); // any car on ne vérifie pas les arguments
    }

    @Test
    void shouldSupportAndEnableWhenPrepareSocketGivenTypicalProtocols() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        String[] supportedProtocols = {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        String[] enabledProtocols = {"SSLv3", "TLSv1"};

        when(mockSocket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(mockSocket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(mockSocket);

        String[] expectedProtocols = {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"};
        verify(mockSocket).setEnabledProtocols(argThat(protocols -> 
            Arrays.equals(protocols, expectedProtocols)
        ));
    }
}