package musicStore.redirect;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MusicStoreHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "You get redirected to a secure website";
        Headers responseHeader = httpExchange.getResponseHeaders();
        responseHeader.set("Location", "https://localhost:12345/MusicStore");
        httpExchange.sendResponseHeaders(301, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
