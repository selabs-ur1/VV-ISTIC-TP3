package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Mock
    private SSLSocket mockSSLSocket;

    @BeforeEach
    public void beforeEach(){
        mockSSLSocket = mock(SSLSocket.class);
    }

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory f = new TLSSocketFactory();

        when(mockSSLSocket.getEnabledProtocols())
                .thenReturn(null);

        when(mockSSLSocket.getSupportedProtocols())
                .thenReturn(null);

        doNothing().when(mockSSLSocket).setEnabledProtocols(any());

        f.prepareSocket(mockSSLSocket);

        verify(mockSSLSocket, times(1)).getEnabledProtocols();
        verify(mockSSLSocket, times(1)).getSupportedProtocols();
        //setEnabledProtocols should not be called when its argument is null or empty
        verify(mockSSLSocket, times(0)).setEnabledProtocols(new String[]{});
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        when(mockSSLSocket.getSupportedProtocols())
                .thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSSLSocket.getEnabledProtocols())
                .thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        doNothing().when(mockSSLSocket).setEnabledProtocols(any());

        f.prepareSocket(mockSSLSocket);

        verify(mockSSLSocket, times(1)).getEnabledProtocols();
        verify(mockSSLSocket, times(1)).getSupportedProtocols();
        //verify that the arguments passed to setEnabledProtocols are correct
        verify(mockSSLSocket, times(1)).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1",
                "TLSv1", "SSLv3"});
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
