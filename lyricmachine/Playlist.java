import java.util.ArrayList;

public class Playlist {
    String title;
    String owner;
    String duration;
    ArrayList<String> trackList;
    ArrayList<String> artistList;

    public Playlist(){

    }

    public Playlist(String title, String owner, String duration, ArrayList<String> trackList, ArrayList<String> artistList){
        this.title = title;
        this.owner = owner;
        this.duration = duration;
        this.trackList = trackList;
        this.artistList = artistList;
    }

    public String getTitle(){
        return title;
    }
    public String getOwner(){
        return owner;
    }
    public String getDuration(){
        return duration;
    }
    public ArrayList<String> getTrackList(){
        return trackList;
    }
    public ArrayList<String> getArtistList(){
        return artistList;
    }






}