import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.shuffle;
import static org.junit.jupiter.api.Assertions.;
import static org.mockito.Mockito.;

public class TLSSocketFactoryTestMocks {

    String[] protocols = new String[]{};

    @Test
    public void preparedSocket_NullProtocols() {

        TLSSocketFactory f = new TLSSocketFactory();

        // Créer un mock pour SSLSocket
        SSLSocket mockSocket = mock(SSLSocket.class);


        // Définir le comportement attendu pour le mock
        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[0]);
        //when(mockSocket.setEnabledProtocols(protocols)).thenReturn(fail());
        f.prepareSocket(mockSocket);

        //assertEquals(mockSocket.getEnabledProtocols(), null);
        verify(mockSocket, never()).setEnabledProtocols(any()); // verify that the function was never called
    }

    @Test
    public void typical() {
        // Arrange
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class);

        // Stubbing
        when(sslSocketMock.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(sslSocketMock.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        // Act
        f.prepareSocket(sslSocketMock);

        // Assert
        // Verify that setEnabledProtocols was called with the expected arguments
        verify(sslSocketMock).setEnabledProtocols(argThat(array -> Arrays.equals(array, new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"})));
    }

}