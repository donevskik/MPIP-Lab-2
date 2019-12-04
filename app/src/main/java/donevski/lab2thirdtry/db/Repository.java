package donevski.lab2thirdtry.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import donevski.lab2thirdtry.models.MovieDetails;

import java.util.List;

public class Repository {
    private MovieDetailsDao movieDetailsDao;
//    private LiveData<List<MovieDetails>> allMovies;

    public Repository(Application application) {
        OMDBDatabase database = OMDBDatabase.getInstance(application);
        movieDetailsDao = database.getMovieDetailsDao();
//        allMovies = movieDetailsDao.getAll();
    }

    public void insert(final MovieDetails movieDetails) {
        new InsertMovieAsyncTask(movieDetailsDao).execute(movieDetails);
    }

    public void deleteAll() {
        new DeleteAllMoviesAsyncTask(movieDetailsDao).execute();
    }

    public LiveData<List<MovieDetails>> getAllMovies() {
//        return allMovies;
        return movieDetailsDao.getAll();
    }

    public LiveData<MovieDetails> getMovie(String imdbID){
        return movieDetailsDao.get(imdbID);
    }

    private static class InsertMovieAsyncTask extends AsyncTask<MovieDetails, Void, Void> {

        private MovieDetailsDao movieDetailsDao;

        public InsertMovieAsyncTask(MovieDetailsDao movieDetailsDao) {
            this.movieDetailsDao = movieDetailsDao;
        }

        @Override
        protected Void doInBackground(MovieDetails... movieDetails) {
            movieDetailsDao.insert(movieDetails[0]);
            return null;
        }
    }

    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {

        private MovieDetailsDao movieDetailsDao;

        public DeleteAllMoviesAsyncTask(MovieDetailsDao movieDetailsDao) {
            this.movieDetailsDao = movieDetailsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDetailsDao.deleteAll();
            return null;
        }
    }
}
