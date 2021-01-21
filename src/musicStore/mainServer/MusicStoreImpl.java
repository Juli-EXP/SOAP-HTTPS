package musicStore.mainServer;

import javax.jws.WebService;
import java.util.ArrayList;


@WebService(
        name = "MusicStore",
        serviceName = "MusicStore",
        targetNamespace = "https://localhost:12345/MusicStore",
        endpointInterface = "musicStore.mainServer.MusicStoreInterface"
)
public class MusicStoreImpl implements MusicStoreInterface{
    private final ArrayList<Band> bands;

    public MusicStoreImpl() {
        bands = new ArrayList<>();
    }

    public MusicStoreImpl(ArrayList<Band> bands) {
        this.bands = bands;
    }


    @Override
    public Double getPrice(String bandName, String albumName) {
        for (Band b : bands) {
            if (b.getName().equals(bandName)) {
                for (Album a : b.getAlbums()) {
                    if (a.getName().equals(albumName)) {
                        return a.getPrice();
                    }
                }
            }
        }

        return 0d;
    }

    @Override
    public Album[] getAllAlbums(String bandName) {
        for (Band b : bands) {
            if (b.getName().equals(bandName)) {
                return b.getAlbums().toArray(new Album[0]);
            }
        }

        return new Album[0];
    }

    @Override
    public Album purchaseAlbum(String bandName, String albumName) {
        for (Band b : bands) {
            if (b.getName().equals(bandName)) {
                for (Album a : b.getAlbums()) {
                    if (a.getName().equals(albumName)) {
                        return a;
                    }
                }
            }
        }

        return new Album();
    }
}
