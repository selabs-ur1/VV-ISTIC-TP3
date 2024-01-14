package fr.istic.vv;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    public static SSLSocket createMockSSLSocketWithNullProtocols() {
        SSLSocket mockSSLSocket = Mockito.mock(SSLSocket.class);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);
        return mockSSLSocket;
    }

    public static SSLSocket createTypicalMockSSLSocket() {
        SSLSocket mockSSLSocket = Mockito.mock(SSLSocket.class);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        return mockSSLSocket;
    }

    private static String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
