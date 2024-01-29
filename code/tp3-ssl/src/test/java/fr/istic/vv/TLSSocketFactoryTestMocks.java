package fr.istic.vv;

import org.junit.jupiter.api.Test;
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
        SSLSocket mockSSLSocket = createSSLSocketMock(null, null);

        f.prepareSocket(mockSSLSocket);

        assertNull(mockSSLSocket.getEnabledProtocols());
        assertNull(mockSSLSocket.getSupportedProtocols());

        Mockito.verify(mockSSLSocket, Mockito.never()).setEnabledProtocols(Mockito.any());
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSSLSocket = createSSLSocketMock(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}), shuffle(new String[]{"SSLv3", "TLSv1"}));

        f.prepareSocket(mockSSLSocket);

        Mockito.verify(mockSSLSocket, Mockito.atLeastOnce()).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

    private SSLSocket createSSLSocketMock(String[] supportedProtocols, String[] enabledProtocols){
        SSLSocket mockSSLSocket = Mockito.mock(SSLSocket.class);

        Mockito.when(mockSSLSocket.getSupportedProtocols()).thenReturn(supportedProtocols);
        Mockito.when(mockSSLSocket.getEnabledProtocols()).thenReturn(enabledProtocols);

        return mockSSLSocket;
    }

}
