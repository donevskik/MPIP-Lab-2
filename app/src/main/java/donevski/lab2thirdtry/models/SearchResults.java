package donevski.lab2thirdtry.models;

import com.google.gson.annotations.SerializedName;
import retrofit2.Call;

import java.util.List;

public class SearchResults {
    @SerializedName("Search")
    private List<Movie> results;
    private String totalResults; //might be int or sth
    private boolean Response;


    public String getTotalResults() {
        return totalResults;
    }

    public boolean isResponse() {
        return Response;
    }

    public List<Movie> getResults() {
        return results;
    }
}
