package musicStore.mainServer;

import java.util.ArrayList;

public class Band {
    private final String name;
    private final ArrayList<Album> albums;

    public Band(String name, ArrayList<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public Band(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }
}
