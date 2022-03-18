package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TLSSocketFactoryTestMocks extends Mockito{

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols(@Mock SSLSocket socket)  {
        TLSSocketFactory f = new TLSSocketFactory();
        when(socket.getEnabledProtocols()).thenReturn(null);
        when(socket.getSupportedProtocols()).thenReturn(null);
        f.prepareSocket(socket);
        verify(socket, times(0)).setEnabledProtocols(new String[]{});
        verify(socket, times(1)).getSupportedProtocols();
        verify(socket, times(1)).getEnabledProtocols();
    }

    @Test
    public void typical(@Mock SSLSocket socket)  {

        TLSSocketFactory f = new TLSSocketFactory();
        when(socket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(socket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        f.prepareSocket(socket);
        verify(socket, times(1)).getEnabledProtocols();
        verify(socket, times(1)).getEnabledProtocols();
        verify(socket, times(1)).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}