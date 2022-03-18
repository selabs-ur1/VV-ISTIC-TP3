package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    //Mock attribute
    SSLSocket mockedSocket = mock(SSLSocket.class);

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();

        //Mock
        when(mockedSocket.getSupportedProtocols()).thenReturn(null);
        when(mockedSocket.getEnabledProtocols()).thenReturn(null);
        doAnswer(invocationOnMock -> {
            fail();
            return null;
        }).when(mockedSocket).setEnabledProtocols(any(String[].class));

        //Test
        f.prepareSocket(mockedSocket);
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        //Mock
        when(mockedSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockedSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        doAnswer(invocationOnMock -> {
            assertTrue(Arrays.equals(invocationOnMock.getArgument(0), new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }));
            return null;
        }).when(mockedSocket).setEnabledProtocols(any(String[].class));

        //Test
        f.prepareSocket(mockedSocket);
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}