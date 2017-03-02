package technology.xor.amr;

public class Site {
    String name;
    String description;
    int photoId;

    Site(String name, String description, int photoId) {
        this.name = name;
        this.description = description;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }
}


