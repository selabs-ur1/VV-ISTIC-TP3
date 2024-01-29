package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//Authors: Dufeil Jaufret & Gentile Brian

public class TLSSocketFactoryTestMocks {


    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory tlSSocketFactory = new TLSSocketFactory();
        SSLSocket mockSocket = Mockito.mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        tlSSocketFactory.prepareSocket(mockSocket);

    }

    @Test
    public void typical() {
        TLSSocketFactory tlSSocketFactory = new TLSSocketFactory();
        SSLSocket mockSocket = Mockito.mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        tlSSocketFactory.prepareSocket(mockSocket);

        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});

    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}