package donevski.lab2thirdtry.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import donevski.lab2thirdtry.asynctask.MovieDetailsAsyncTask;
import donevski.lab2thirdtry.db.Repository;
import donevski.lab2thirdtry.models.MovieDetails;

import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {

    private Repository repository;

    private String searchBy;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        fetchData();
    }

    public LiveData<List<MovieDetails>> getAllMovies(){
        return repository.getAllMovies();
    }

    private void fetchData(){
        MovieDetailsAsyncTask movieDetailsAsyncTask = new MovieDetailsAsyncTask(repository);
        movieDetailsAsyncTask.execute(searchBy);
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }
}
