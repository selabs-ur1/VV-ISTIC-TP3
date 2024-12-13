package fr.istic.vv;

public interface SSLSocket {

    String[] getSupportedProtocols() ;

    String[] getEnabledProtocols();

    void setEnabledProtocols(String[] protocols);

}
