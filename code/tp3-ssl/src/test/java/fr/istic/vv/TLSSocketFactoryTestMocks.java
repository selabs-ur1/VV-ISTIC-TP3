package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TLSSocketFactoryTestMocks {

    private TLSSocketFactory factory;
    private SSLSocket mockSocket;

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }


    @BeforeEach
    public void setUp() {
        factory = new TLSSocketFactory();
        mockSocket = Mockito.mock(SSLSocket.class);
    }

    @Test
    public void preparedSocket_NullProtocols() {
        Mockito.when(mockSocket.getEnabledProtocols()).thenReturn(null);
        Mockito.when(mockSocket.getSupportedProtocols()).thenReturn(null);

        factory.prepareSocket(mockSocket);

        verify(mockSocket, times(0)).setEnabledProtocols(any());
    }

    @Test
    public void typical()  {

        String[] supportedProtocols = shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        String[] enabledProtocols = shuffle(new String[]{"SSLv3", "TLSv1"});
    

        Mockito.when(mockSocket.getSupportedProtocols()).thenReturn(supportedProtocols);
        Mockito.when(mockSocket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(mockSocket);

        verify(mockSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }


}