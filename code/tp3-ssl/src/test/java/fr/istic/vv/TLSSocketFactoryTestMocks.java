package fr.istic.vv;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mock Test class for TLSSocketFactory
 */
public class TLSSocketFactoryTestMocks {

    @Mock
    private static SSLSocket sslSocket;

    @BeforeAll
    public static void setUp() {
        sslSocket = Mockito.mock(SSLSocket.class);
    }

    @AfterAll
    public static void tearDown() {
    }

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory f = new TLSSocketFactory();
        Mockito.when(sslSocket.getEnabledProtocols()).thenReturn(null);
        Mockito.when(sslSocket.getSupportedProtocols()).thenReturn(null);
        f.prepareSocket(sslSocket);
        Mockito.verify(sslSocket, Mockito.times(1)).getEnabledProtocols();
        Mockito.verify(sslSocket, Mockito.times(1)).getSupportedProtocols();
        Mockito.verify(sslSocket, Mockito.times(0)).setEnabledProtocols(new String[]{});
    }

    @Test
    public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();
        Mockito.when(sslSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        Mockito.when(sslSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        Mockito.doNothing().when(sslSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
        f.prepareSocket(sslSocket);
        Mockito.verify(sslSocket, Mockito.times(1)).getEnabledProtocols();
        Mockito.verify(sslSocket, Mockito.times(1)).getEnabledProtocols();
        Mockito.verify(sslSocket, Mockito.times(1)).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}
