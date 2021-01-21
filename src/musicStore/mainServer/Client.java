package musicStore.mainServer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Client {
    public static void main(String[] args) throws IOException {
        MusicStoreInterface musicStore = setupConnection("https://localhost:12345/MusicStore");
        //MusicStoreInterface musicStore = setupConnection("http://localhost:80/MusicStore");

        System.out.println(musicStore.purchaseAlbum("Metallica", "Master of Puppets"));
    }

    private static MusicStoreInterface setupConnection(String url) throws IOException {
        //Setup Truststore
        System.getProperties().put("javax.net.ssl.trustStore", "src/util/client_truststore.ks");
        System.getProperties().put("javax.net.ssl.trustStorePassword", "password");

        //Check for redirect
        String newUrl = checkRedirect(url);

        //Send username and password
        boolean authOk = authenticate("", "");


        URL wsdlUrl = new URL(newUrl + "?wsdl");
        QName qName = new QName(newUrl, "MusicStore");
        Service service = Service.create(wsdlUrl, qName);
        return service.getPort(MusicStoreInterface.class);
    }

    private static String checkRedirect(String url) throws IOException{
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        boolean redirect = false;

        //Get the response code from the website
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }

        String newUrl = "";
        if (redirect) {
            newUrl = connection.getHeaderField("Location");
        } else {
            newUrl = url;
        }

        return newUrl;
    }


    private static boolean authenticate(String user, String password){
        return true;
    }
}
