package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TLSSocketFactoryTestMocks {

    @Test
    void preparedSocket_NullProtocols() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = mock(SSLSocket.class);

        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(socket);

        verify(socket, never()).setEnabledProtocols(any());
    }

    @Test
    void prepareSocket_TypicalCase() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = mock(SSLSocket.class);

        String[] supportedProtocols = shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        String[] enabledProtocols = shuffle(new String[]{"SSLv3", "TLSv1"});

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        f.prepareSocket(socket);

        String[] expectedProtocols = {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"};
        verify(socket).setEnabledProtocols(eq(expectedProtocols));
    }

    @Test
    void prepareSocket_SupportedOnly() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1.2", "TLSv1.1"});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{});

        factory.prepareSocket(mockSocket);

        String[] expectedProtocols = {"TLSv1.2", "TLSv1.1"};
        verify(mockSocket).setEnabledProtocols(expectedProtocols);
    }

    @Test
    void prepareSocket_EnabledOnly() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{"TLSv1.1"});

        factory.prepareSocket(mockSocket);

        String[] expectedProtocols = {"TLSv1.1"};
        verify(mockSocket).setEnabledProtocols(expectedProtocols);
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}