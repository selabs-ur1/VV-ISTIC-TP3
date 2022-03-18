package fr.istic.vv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TLSSocketFactoryTestMocks {

    @Mock
    private SSLSocket sslSocket;
    @Mock
    private TLSSocketFactory factory;

    @Test
    public void preparedSocket_NullProtocolsTest() {
        //BEGIN
        TLSSocketFactory f = new TLSSocketFactory();
        String[] target = new String[0];

        //WHEN
        when(sslSocket.getEnabledProtocols()).thenReturn(null);
        when(sslSocket.getSupportedProtocols()).thenReturn(null);
        f.prepareSocket(sslSocket);

        //THEN
    }

    @Test
    public void typical() {
        //BEGIN
        TLSSocketFactory f = new TLSSocketFactory();
        String[] supportedProtocols = new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        String[] enabledProtocols = new String[] {"SSLv3", "TLSv1"};
        //WHEN

        when(sslSocket.getSupportedProtocols()).thenReturn(new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(sslSocket.getEnabledProtocols()).thenReturn(new String[] {"SSLv3", "TLSv1"});
        f.prepareSocket(sslSocket);

        //THEN
        Assertions.assertTrue(sslSocket.getSupportedProtocols().length == 5);
        Assertions.assertTrue(sslSocket.getEnabledProtocols().length == 2);

        Assertions.assertArrayEquals(supportedProtocols, sslSocket.getSupportedProtocols());
        Assertions.assertArrayEquals(enabledProtocols, sslSocket.getEnabledProtocols());
    }

    @Test
    public void typicalUpgrade() {
        //BEGIN
        String[] supportedProtocols = new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        String[] enabledProtocols = new String[] {"SSLv3", "TLSv1"};
        String[] computed = new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3", "TLSv1"};

        //WHEN
        when(sslSocket.getSupportedProtocols()).thenReturn(new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(sslSocket.getEnabledProtocols()).thenReturn(new String[] {"SSLv3", "TLSv1"});
        when(factory.getTarget()).thenReturn(new String[] {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3", "TLSv1"});

        //THEN
        String[] test = factory.getTarget();

        Assertions.assertEquals(5, sslSocket.getSupportedProtocols().length);
        Assertions.assertEquals(2, sslSocket.getEnabledProtocols().length);
        Assertions.assertEquals(7, test.length);



        Assertions.assertArrayEquals(supportedProtocols, sslSocket.getSupportedProtocols());
        Assertions.assertArrayEquals(enabledProtocols, sslSocket.getEnabledProtocols());
        Assertions.assertArrayEquals(computed, test);

    }


}