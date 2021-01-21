package musicStore.mainServer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;


public class Client {
    public static void main(String[] args) throws IOException {
        System.getProperties().put("javax.net.ssl.trustStore", "src/util/client_truststore.ks");
        System.getProperties().put("javax.net.ssl.trustStorePassword", "password");


        URL wsdlUrl = new URL("https://localhost:12345/MusicStore?wsdl");
        QName qName = new QName("https://localhost:12345/MusicStore", "MusicStore");
        Service service = Service.create(wsdlUrl, qName);
        MusicStoreInterface musicStore = service.getPort(MusicStoreInterface.class);

        System.out.println(musicStore.purchaseAlbum("Metallica", "Master of Puppets"));
    }
}
