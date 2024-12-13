package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TLSSocketFactoryTestMocks {
    /**
     * Test when the edge case when the both supported and enabled protocols are
     * null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        doAnswer(invocation -> {
            fail();
            return null;
        }).when(mockSocket).setEnabledProtocols(any(String[].class));

        f.prepareSocket(mockSocket);
    }

    @Test
    public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        doAnswer(invocation -> {
            String[] protocols = invocation.getArgument(0);
            assertTrue(Arrays.equals(protocols, new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"}));
            return null;
        }).when(mockSocket).setEnabledProtocols(any(String[].class));

        f.prepareSocket(mockSocket);

        // NEW : verify that the methods were called exactly once
        verify(mockSocket).getSupportedProtocols();
        verify(mockSocket).getEnabledProtocols();
        verify(mockSocket).setEnabledProtocols(any(String[].class)); // No need to test the values here as it is already done in the doAnswer
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
