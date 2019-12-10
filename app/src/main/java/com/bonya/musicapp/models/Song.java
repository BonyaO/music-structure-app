package com.bonya.musicapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Song implements Parcelable {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int playbackTimeInSeconds;




    public Song(String title, String artist, String album, String genre, int playbackTimeInSeconds) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.playbackTimeInSeconds = playbackTimeInSeconds;
    }

    protected Song(Parcel in) {
        title = in.readString();
        artist = in.readString();
        album = in.readString();
        genre = in.readString();
        playbackTimeInSeconds = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPlaybackTimeInSeconds() {
        return playbackTimeInSeconds;
    }

    public void setPlaybackTimeInSeconds(int playbackTimeInSeconds) {
        this.playbackTimeInSeconds = playbackTimeInSeconds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(artist);
        parcel.writeString(album);
        parcel.writeString(genre);
        parcel.writeInt(playbackTimeInSeconds);
    }
    public ArrayList<Song> getAlbumSubList(ArrayList<Song> allsongs, String album){
        ArrayList<Song> songs = new ArrayList<>();
        for(Song song: allsongs){
            if(song.getAlbum().equals(album)){
                songs.add(song);
            }
        }
        return songs;
    }
}
