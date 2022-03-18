package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory f = mock(TLSSocketFactory.class);

        SSLSocket sslsocket = mock(SSLSocket.class);
        when(sslsocket.getEnabledProtocols()).thenReturn(null);
        when(sslsocket.getSupportedProtocols()).thenReturn(null);
        doAnswer((protocols) ->
                {   fail();
                    return true;
                }
        ).when(sslsocket).setEnabledProtocols(any(String[].class));
        f.prepareSocket(sslsocket);
    }

    @Test
    public void typical() {
        TLSSocketFactory f = mock(TLSSocketFactory.class);

        SSLSocket sslsocket = mock(SSLSocket.class);
        when(sslsocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"3","5"}));
        when(sslsocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"1","2","3","4","5","6"}));
        doAnswer((protocols) ->
        {
            assertTrue(Arrays.equals(protocols.getArguments(), new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"}));
            return true;
        }
        ).when(sslsocket).setEnabledProtocols(any(String[].class));
        f.prepareSocket(sslsocket);
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}
