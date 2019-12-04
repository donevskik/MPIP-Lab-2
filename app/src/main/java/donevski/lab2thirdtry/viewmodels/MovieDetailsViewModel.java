package donevski.lab2thirdtry.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import donevski.lab2thirdtry.asynctask.MovieDetailsAsyncTask;
import donevski.lab2thirdtry.db.Repository;
import donevski.lab2thirdtry.models.MovieDetails;

import java.util.List;
import java.util.logging.Logger;

public class MovieDetailsViewModel extends AndroidViewModel {

    private Repository repository;

    private String searchBy;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        //fetchData();
    }

    public LiveData<List<MovieDetails>> getAllMovies(){
        return repository.getAllMovies();
    }

    public LiveData<MovieDetails> getMovie(String imdbID){
        return repository.getMovie(imdbID);
    }

    private void fetchData(){
        MovieDetailsAsyncTask movieDetailsAsyncTask = new MovieDetailsAsyncTask(repository);
        movieDetailsAsyncTask.execute(searchBy);
        Logger logger = Logger.getLogger("MainActivity");
        logger.info("FETCH DATA CALLED");
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
        fetchData();
    }
}
