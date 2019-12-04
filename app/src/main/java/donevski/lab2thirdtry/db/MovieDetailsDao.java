package donevski.lab2thirdtry.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import donevski.lab2thirdtry.models.MovieDetails;

import java.util.List;

@Dao
public interface MovieDetailsDao {
    @Insert
    void insert(MovieDetails movie);

    @Delete
    void delete(MovieDetails movie);

    @Query("DELETE FROM movie_details")
    void deleteAll();

    @Query("SELECT * FROM movie_details")
    LiveData<List<MovieDetails>> getAll();

    @Query("SELECT * FROM movie_details WHERE imdbID = :imdbID")
    LiveData<MovieDetails> get(String imdbID);
}
