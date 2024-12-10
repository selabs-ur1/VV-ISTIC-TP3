package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;


public class TLSSocketFactoryTestMocks {

    @Test
    public void prepareSocket_NullProtocols() {
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

    @Test
    public void prepareSocket_TypicalCase() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    @Test
    public void prepareSocket_SupportedOnly() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1.2", "TLSv1.1"});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{});

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1"});
    }

    @Test
    public void prepareSocket_EnabledOnly() {
        // Arrange
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3"});

        // Act
        factory.prepareSocket(mockSocket);

        // Assert
        verify(mockSocket).setEnabledProtocols(new String[]{"SSLv3"});
    }

}