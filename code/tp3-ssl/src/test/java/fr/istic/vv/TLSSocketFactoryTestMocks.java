package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class);

        when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(null);

        tlsSocketFactory.prepareSocket(sslSocketMock);

        verify(sslSocketMock, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    public void typical() {
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class);

        when(sslSocketMock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(sslSocketMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        tlsSocketFactory.prepareSocket(sslSocketMock);

        verify(sslSocketMock).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}