package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTest {

    /**
     * Test when both supported and enabled protocols are null.
     */
    @Test
    public void testPrepareSocket_NullProtocols() {
        // Arrange
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        SSLSocket mockSSLSocket = mock(SSLSocket.class);

        // Stub behavior for getSupportedProtocols and getEnabledProtocols
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);

        // Act and Assert
        assertDoesNotThrow(() -> tlsSocketFactory.prepareSocket(mockSSLSocket));

        // Verify that setEnabledProtocols was not called
        verify(mockSSLSocket, never()).setEnabledProtocols(any());
    }

    /**
     * Typical test case with shuffled protocols.
     */
    @Test
    public void testPrepareSocket_TypicalCase() {
        // Arrange
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        SSLSocket mockSSLSocket = mock(SSLSocket.class);

        // Stub behavior for getSupportedProtocols and getEnabledProtocols
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        // Act and Assert
        assertDoesNotThrow(() -> tlsSocketFactory.prepareSocket(mockSSLSocket));

        // Verify that setEnabledProtocols was called with the expected protocols
        verify(mockSSLSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    // Helper method to shuffle an array
    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}