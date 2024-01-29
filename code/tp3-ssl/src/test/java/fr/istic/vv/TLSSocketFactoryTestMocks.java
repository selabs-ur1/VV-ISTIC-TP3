package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mock = Mockito.mock(SSLSocket.class);
        Mockito.when(mock.getSupportedProtocols()).thenReturn(null);
        Mockito.when(mock.getEnabledProtocols()).thenReturn(null);




        f.prepareSocket(mock);

        assertNull(mock.getEnabledProtocols());
        assertNull(mock.getSupportedProtocols());

        Mockito.verify(mock, Mockito.never()).setEnabledProtocols(Mockito.any()); //setEnabledProtocol test : never called
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mock = Mockito.mock(SSLSocket.class);
        Mockito.when(mock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        Mockito.when(mock.getSupportedProtocols()).thenReturn(shuffle(shuffle(new String[]{"SSLv3", "TLSv1"})));

        f.prepareSocket(mock);

    }
    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}