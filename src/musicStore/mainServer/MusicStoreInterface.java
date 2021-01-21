package musicStore.mainServer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(
        name = "MusicStore",
        serviceName = "MusicStore",
        targetNamespace = "https://localhost:12345/MusicStore"
)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MusicStoreInterface {
    @WebMethod(operationName = "getPrice")
    Double getPrice(
            @WebParam(name = "bandName") String bandName,
            @WebParam(name = "albumName") String albumName
    );

    @WebMethod(operationName = "getAllAlbums")
    Album[] getAllAlbums(
            @WebParam(name = "bandName") String bandName

    );

    @WebMethod(operationName = "purchaseAlbum")
    Album purchaseAlbum(
            @WebParam(name = "bandName") String bandName,
            @WebParam(name = "albumName") String albumName
    );
}
