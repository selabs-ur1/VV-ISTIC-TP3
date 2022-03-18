package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TLSSocketFactoryTestMocks {

  @Mock
	SSLSocket socket;

	/**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        assertNotNull(socket);
        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(null);
        verify(socket, never()).setEnabledProtocols(any(String[].class));
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(socket);
    }
    
    @Test
    public void typical()  {
    	assertNotNull(socket);
    	when(socket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(socket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                String[] protocols = (String[]) args[0];
                assertTrue(Arrays.equals(protocols, new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }));
                return null;
              }
          }).when(socket).setEnabledProtocols(any(String[].class));
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(socket);
    }

}
