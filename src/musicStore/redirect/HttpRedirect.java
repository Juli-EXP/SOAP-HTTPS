package musicStore.redirect;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class HttpRedirect {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 80), 0);
        server.createContext("/MusicStore", new MusicStoreHttpHandler());
        server.setExecutor(null);
        server.start();
    }
}
