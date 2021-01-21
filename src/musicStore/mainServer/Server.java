package musicStore.mainServer;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.ws.Endpoint;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.ArrayList;


public class Server {
    private static final ArrayList<Band> bands = new ArrayList<>();
    public static void main(String[] args){
        setupBands();

        //startHttpServer();
        startHttpsServer();

        System.out.println("Server available");
    }

    private static void startHttpServer(){
        Endpoint.publish("http://localhost:12345/MusicStore", new MusicStoreImpl(bands));
    }

    private static void startHttpsServer(){
        try {
            HttpsServer httpsServer = createHttpsServer();

            HttpContext httpContext = httpsServer.createContext("/MusicStore");
            Endpoint endpoint = Endpoint.create(new MusicStoreImpl(bands));
            endpoint.publish(httpContext);

            httpsServer.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static HttpsServer createHttpsServer() throws Exception{
        //Create Keystore
        char[] keystorePassword = "password".toCharArray();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream("src/util/server_keystore.ks"), keystorePassword);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, keystorePassword);

        //Create SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        //Create HTTPS server
        HttpsConfigurator httpsConfigurator = new HttpsConfigurator(sslContext);
        HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress("localhost", 12345), 0);
        httpsServer.setHttpsConfigurator(httpsConfigurator);

        return httpsServer;
    }

    private static void setupBands(){
        Band band1 = new Band("Metallica");
        Band band2 = new Band("Slayer");

        Server.bands.add(band1);
        Server.bands.add(band2);

        band1.addAlbum(new Album(
                "Ride the Lightning",
                8.99
        ));

        band1.addAlbum(new Album(
                "Master of Puppets",
                9.99
        ));

        band2.addAlbum(new Album(
                "South of Heaven",
                8.99
        ));
    }
}
