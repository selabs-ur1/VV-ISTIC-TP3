import fr.istic.vv.SSLSocket;
import fr.istic.vv.TLSSocketFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory tlSSocketFactory = new TLSSocketFactory();
        SSLSocket mockedSocket = Mockito.mock(SSLSocket.class);

        when(mockedSocket.getSupportedProtocols()).thenReturn(null);
        when(mockedSocket.getEnabledProtocols()).thenReturn(null);

        tlSSocketFactory.prepareSocket(mockedSocket);

        verify(mockedSocket, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    public void typical() {
        TLSSocketFactory tlSSocketFactory = new TLSSocketFactory();
        SSLSocket mockedSocket = Mockito.mock(SSLSocket.class);

        when(mockedSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockedSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        tlSSocketFactory.prepareSocket(mockedSocket);

        verify(mockedSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
