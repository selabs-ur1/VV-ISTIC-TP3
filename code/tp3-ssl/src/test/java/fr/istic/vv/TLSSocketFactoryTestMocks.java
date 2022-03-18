package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Mock
    private static SSLSocket sslSocketMock;

    @BeforeAll
    public static void setUp() {
        sslSocketMock = Mockito.mock(SSLSocket.class);
    }

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(null);
        doNothing().when(sslSocketMock).setEnabledProtocols(new String[]{});
        f.prepareSocket(sslSocketMock);
        verify(sslSocketMock, times(1)).getSupportedProtocols();
        verify(sslSocketMock, times(1)).getEnabledProtocols();
        verify(sslSocketMock, times(0)).setEnabledProtocols(new String[] {});
    }


    @Test
    public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();
        when(sslSocketMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        when(sslSocketMock.getSupportedProtocols()).thenReturn(shuffle(
                new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        doNothing().when(sslSocketMock).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
        f.prepareSocket(sslSocketMock);
        verify(sslSocketMock, times(1)).getEnabledProtocols();
        verify(sslSocketMock, times(1)).setEnabledProtocols(
                new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}