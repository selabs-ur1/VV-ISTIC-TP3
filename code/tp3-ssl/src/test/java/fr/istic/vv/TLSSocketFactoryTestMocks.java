package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TLSSocketFactoryTestMocks {


     /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();

        // Mock
        SSLSocket sslSocketMock = mock(SSLSocket.class);
        Mockito.when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        Mockito.when(sslSocketMock.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(sslSocketMock);

        verify(sslSocketMock, never()).setEnabledProtocols(any());


    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        // Mock
        SSLSocket sslSocketMock = mock(SSLSocket.class);
        Mockito.when(sslSocketMock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        Mockito.when(sslSocketMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        f.prepareSocket(sslSocketMock);

        verify(sslSocketMock).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }


}