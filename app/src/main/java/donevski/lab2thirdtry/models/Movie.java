package donevski.lab2thirdtry.models;

import com.google.gson.annotations.SerializedName;

public class Movie {

    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    @SerializedName("Poster")
    private String imageUrl;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + Title + '\'' +
                ", year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + Type + '\'' +
                '}';
    }
}
