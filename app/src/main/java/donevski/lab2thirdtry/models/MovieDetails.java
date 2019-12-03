package donevski.lab2thirdtry.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "movie_details")
public class MovieDetails {

    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    @SerializedName("Poster")
    private String imageUrl;
    private List<Rating> Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    @PrimaryKey
    private String imdbID;
    private String Type;
    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;
    private boolean Response;

    // May need constructor

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Rating> getRatings() {
        return Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getDVD() {
        return DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public String getWebsite() {
        return Website;
    }

    public boolean isResponse() {
        return Response;
    }
}
