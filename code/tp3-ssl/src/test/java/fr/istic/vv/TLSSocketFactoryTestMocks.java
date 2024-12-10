package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    /**
     * Test the edge case where both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket, never()).setEnabledProtocols(any());
    }

    /**
     * Test a typical case with shuffled supported and enabled protocols.
     */
    @Test
    public void typical() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        String[] supportedProtocols = { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" };
        String[] enabledProtocols = { "SSLv3", "TLSv1" };

        when(mockSocket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(mockSocket.getEnabledProtocols()).thenReturn(enabledProtocols);

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket).setEnabledProtocols(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }

    /**
     * Test when the supported protocols include none of the preferred TLS
     * protocols.
     */
    @Test
    public void noPreferredProtocolsSupported() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        String[] supportedProtocols = { "SSLv2Hello", "SSLv3" };
        String[] enabledProtocols = { "SSLv3" };

        when(mockSocket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(mockSocket.getEnabledProtocols()).thenReturn(enabledProtocols);

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket).setEnabledProtocols(new String[] { "SSLv3" });
    }

    /**
     * Test when the supported and enabled protocols are both empty.
     */
    @Test
    public void emptyProtocols() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(new String[] {});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[] {});

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket, never()).setEnabledProtocols(any());
    }

}