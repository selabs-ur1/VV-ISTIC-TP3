package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    private static class TestSSLSocket implements SSLSocket {
        private String[] supportedProtocols;
        private String[] enabledProtocols;

        @Override
        public String[] getSupportedProtocols() {
            return supportedProtocols;
        }

        @Override
        public String[] getEnabledProtocols() {
            return enabledProtocols;
        }

        @Override
        public void setEnabledProtocols(String[] protocols) {
            this.enabledProtocols = protocols;
        }

        public void setSupportedProtocols(String[] protocols){
            this.supportedProtocols = protocols;
        }
    }

    @Test
    void testPrepareSocket() {
        // Create a test SSLSocket
        TestSSLSocket testSSLSocket = new TestSSLSocket();
        testSSLSocket.setSupportedProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "TLS"});
        testSSLSocket.setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1"});

        // Create a TLSSocketFactory instance
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();

        // Call the prepareSocket method with the test SSLSocket
        tlsSocketFactory.prepareSocket(testSSLSocket);

        // Check that setEnabledProtocols was called with the expected protocols
        assertArrayEquals(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "TLS"}, testSSLSocket.getEnabledProtocols());
    }
}
