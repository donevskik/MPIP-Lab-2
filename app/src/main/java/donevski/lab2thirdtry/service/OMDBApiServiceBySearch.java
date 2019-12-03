package donevski.lab2thirdtry.service;

import donevski.lab2thirdtry.models.SearchResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApiServiceBySearch {

    @GET("?apikey=760d9a7")
    Call<SearchResults> getSearchResults(@Query("s") String searchBy);
}
