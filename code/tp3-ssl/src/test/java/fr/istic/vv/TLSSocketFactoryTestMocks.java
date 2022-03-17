package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSSLSocket = mock(SSLSocket.class);
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
        doNothing().when(mockSSLSocket).setEnabledProtocols(new String[] {});
        f.prepareSocket(mockSSLSocket);

        //Add for be sure that we pass once in the method (case we remove all the code it will fail)
        verify(mockSSLSocket, times(1)).getEnabledProtocols();
        verify(mockSSLSocket, times(1)).getSupportedProtocols();
        verify(mockSSLSocket, times(0)).setEnabledProtocols(new String[]{});
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSSLSocket = mock(SSLSocket.class);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        doNothing().when(mockSSLSocket).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
        f.prepareSocket(mockSSLSocket);

        verify(mockSSLSocket, times(1)).getEnabledProtocols();
        verify(mockSSLSocket, times(1)).getSupportedProtocols();
        verify(mockSSLSocket, times(1)).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1",
                "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}