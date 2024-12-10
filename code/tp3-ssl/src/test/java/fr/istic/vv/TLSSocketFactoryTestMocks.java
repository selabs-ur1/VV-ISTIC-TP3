package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        // Create a mock SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Define the behavior of the mock
        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        // Create an instance of TLSSocketFactory and prepare the socket
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(mockSocket);

        // Verify the interactions with the mock
        verify(mockSocket).getSupportedProtocols();
        verify(mockSocket).getEnabledProtocols();
    }

    @Test
    public void typical() {
        // Create a mock SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);

        // Define the behavior of the mock
        when(mockSocket.getSupportedProtocols())
                .thenReturn(shuffle(new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }));
        when(mockSocket.getEnabledProtocols()).thenReturn(shuffle(new String[] { "SSLv3", "TLSv1" }));

        // Create an instance of TLSSocketFactory and prepare the socket
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(mockSocket);

        // Verify the interactions with the mock
        verify(mockSocket).getSupportedProtocols();
        verify(mockSocket).getEnabledProtocols();
        verify(mockSocket).setEnabledProtocols(argThat(
                protocols -> Arrays.equals(protocols, new String[] { "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" })));
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}