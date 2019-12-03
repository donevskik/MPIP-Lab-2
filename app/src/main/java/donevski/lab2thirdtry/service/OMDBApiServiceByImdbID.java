package donevski.lab2thirdtry.service;

import donevski.lab2thirdtry.models.MovieDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApiServiceByImdbID {

    @GET("?apikey=760d9a7")
    Call<MovieDetails> getMovieDetails(@Query("i") String imdbID);
}
