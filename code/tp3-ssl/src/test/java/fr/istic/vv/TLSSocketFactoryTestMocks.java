package fr.istic.vv;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    private SSLSocket socket;
    private TLSSocketFactory factory;

    @BeforeEach
    void setUp() {
        socket = mock(SSLSocket.class);
        factory = new TLSSocketFactory();
    }

    @AfterEach
    void tearDown() {
        socket = null;
        factory = null;
    }

    @Test
    public void testPrepareSocketWithNullProtocols() {
        when(socket.getSupportedProtocols()).thenReturn(null);
        when(socket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(socket);

        verify(socket, never()).setEnabledProtocols(any());
    }

    @Test
    public void testPrepareSocketWithTypicalProtocols() {
        String[] supportedProtocols = { "TLSv1.2", "TLSv1" };
        String[] enabledProtocols = { "TLSv1.2" };

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(socket);

        verify(socket).setEnabledProtocols(new String[] { "TLSv1.2" , "TLSv1" });
    }

    @Test
    public void testPrepareSocketWithEmptySupportedProtocols() {
        String[] supportedProtocols = {};
        String[] enabledProtocols = { "TLSv1.1" };

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(socket);

        verify(socket, never()).setEnabledProtocols(new String[] { "TLSv1.2" });
    }

    @Test
    public void testPrepareSocketWithEmptyEnabledProtocols() {
        String[] supportedProtocols = { "TLSv1.1", "TLSv1.2" };
        String[] enabledProtocols = {};

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(socket);

        verify(socket).setEnabledProtocols(new String[] { "TLSv1.2", "TLSv1.1" });
    }

    @Test
    public void testPrepareSocketWithNonMatchingProtocols() {
        String[] supportedProtocols = { "TLSv1.1", "TLSv1.2" };
        String[] enabledProtocols = {};

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(socket);

        verify(socket, never()).setEnabledProtocols(new String[] { "TLSv1.5" });
    }

    @Test
    public void testPrepareSocketWithAllProtocolsDisabled() {
        String[] supportedProtocols = { "TLSv1.1", "TLSv1.2", "TLSv1", "TLS" };
        String[] enabledProtocols = {};

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(socket);

        verify(socket, never()).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1.2", "TLSv1", "TLS" });
    }

    @Test
    public void testPrepareSocketWithAllProtocolsEnabled() {
        String[] supportedProtocols = { };
        String[] enabledProtocols = { "TLSv1.1", "TLSv1.2", "TLSv1", "TLS" };

        when(socket.getSupportedProtocols()).thenReturn(supportedProtocols);
        when(socket.getEnabledProtocols()).thenReturn(enabledProtocols);

        factory.prepareSocket(socket);

        verify(socket).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1.2", "TLSv1", "TLS" });
    }
}