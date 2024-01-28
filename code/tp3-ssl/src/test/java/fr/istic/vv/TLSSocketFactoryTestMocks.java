package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket mockSSLSocket = mock(SSLSocket.class);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(mockSSLSocket);

        // vérifie que setEnabledProtocols n'est jamais appelé du fait que les get retourne null
        verify(mockSSLSocket, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket mockSSLSocket = mock(SSLSocket.class);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        f.prepareSocket(mockSSLSocket);

        verify(mockSSLSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }


}