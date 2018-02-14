package it.hueic.kenhoang.mp3_app.Model;

/**
 * Created by kenhoang on 07/02/2018.
 */

public class Song {
    private String id;
    private String name;
    private String author;
    private String url;

    public Song() {
    }

    public Song(String id, String name, String author, String url) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
