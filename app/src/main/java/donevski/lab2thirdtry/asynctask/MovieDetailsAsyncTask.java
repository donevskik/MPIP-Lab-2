package donevski.lab2thirdtry.asynctask;

import android.os.AsyncTask;
import donevski.lab2thirdtry.db.Repository;
import donevski.lab2thirdtry.models.Movie;
import donevski.lab2thirdtry.models.MovieDetails;
import donevski.lab2thirdtry.models.SearchResults;
import retrofit2.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static donevski.lab2thirdtry.client.OMDBApiClient.getServiceByImdbID;
import static donevski.lab2thirdtry.client.OMDBApiClient.getServiceBySearch;

public class MovieDetailsAsyncTask extends AsyncTask<String, Integer, List<MovieDetails>> {

    private Repository repository;
    private List<String> imdbIDs;
    private static int count = 0;

    public MovieDetailsAsyncTask(Repository repository){
        this.repository = repository;
        imdbIDs = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        repository.deleteAll();
    }

    @Override
    protected List<MovieDetails> doInBackground(String... strings) {
        Call<SearchResults> searchResultsCall = getServiceBySearch().getSearchResults(strings[0]);
        count++;
        if (count == 1){
            count = 0;
        }

        try {
            SearchResults searchResults = searchResultsCall.execute().body();
//            searchResults.getResults().stream()
            if (searchResults.getResults() != null){
                for (Movie movie : searchResults.getResults()) {
                    imdbIDs.add(movie.getImdbID());
                }
            }
            //else do sth if there are no movies

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<MovieDetails> ret = new ArrayList<>();
        for (String imdbID : imdbIDs) {
            Call<MovieDetails> movieDetailsCall = getServiceByImdbID().getMovieDetails(imdbID);

            try {
                ret.add(movieDetailsCall.execute().body());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    @Override
    protected void onPostExecute(List<MovieDetails> movieDetails) {
        for(MovieDetails movie : movieDetails){
            repository.insert(movie);
        }
    }
}
