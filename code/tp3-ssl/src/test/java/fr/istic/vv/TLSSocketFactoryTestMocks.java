package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class); //pour le mock des SSLSockets

        when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(sslSocketMock); //appel de la methode
        verify(sslSocketMock, never()).setEnabledProtocols(any()); //pas de protocoles
    }

    @Test
    public void preparedSocket_casGeneraux()  {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class); //pour le mock des SSLSockets

        //tableau de string comportant les protocoles supportes
        String[] protocoleSupportes = shuffle(new String[]{"SSLv2Hello","SSLv3","TLSv1","TLSv1.1","TLSv1.2"});
        //tableau de string comportant les protocoles actives
        String[] protocolesActives = shuffle(new String[]{"SSLv3","TLSv1"});

        when(sslSocketMock.getSupportedProtocols()).thenReturn(protocoleSupportes);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(protocolesActives);

        factory.prepareSocket(sslSocketMock); //appel de la methode

        //tableau de string contenant les protocoles attendus en sortie de la methode pour le verify
        String[] protocolesAttendus = shuffle(new String[]{"SSLv1.2","SSLv1.1","TLSv1","SSLv3" });
        verify(sslSocketMock, never()).setEnabledProtocols(eq(protocolesAttendus)); //verifie que tous les protocoles attendus soient bien renvoyés
    }


    @Test
    public void preparedSocket_ProtoSupportesSeulement()  {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class); //pour le mock des SSLSockets

        when(sslSocketMock.getSupportedProtocols()).thenReturn(new String[]{"TLSv1.2","TLSv1.1"});
        when(sslSocketMock.getEnabledProtocols()).thenReturn(new String[]{});

        factory.prepareSocket(sslSocketMock); //appel de la methode

        String[] protosAttendus = {"TLSv1.2", "TLSv1.1"};
        verify(sslSocketMock).setEnabledProtocols(protosAttendus); //pas de protocoles
    }

    @Test
    public void preparedSocket_ProtoActivesSeulement()  {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket sslSocketMock = mock(SSLSocket.class); //pour le mock des SSLSockets

        when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(new String[]{"TLSv1.2", "TLSv1.1"});

        factory.prepareSocket(sslSocketMock); //appel de la methode

        String[] protosAttendus={"TLSv1.2", "TLSv1.1"};
        verify(sslSocketMock).setEnabledProtocols(protosAttendus);
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }




}