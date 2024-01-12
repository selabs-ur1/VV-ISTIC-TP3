package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket monMock = Mockito.mock(SSLSocket.class);

        Mockito.when(monMock.getSupportedProtocols()).thenReturn(null);
        Mockito.when(monMock.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(monMock);

        Mockito.verify(monMock, Mockito.never()).setEnabledProtocols(any());

        Mockito.verify(monMock).getEnabledProtocols();
        Mockito.verify(monMock).getSupportedProtocols();

    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        SSLSocket monMock = Mockito.mock(SSLSocket.class);

        Mockito.when(monMock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        Mockito.when(monMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        f.prepareSocket(monMock);

        Mockito.verify(monMock).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });

        Mockito.verify(monMock).getEnabledProtocols();
        Mockito.verify(monMock).getSupportedProtocols();
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}