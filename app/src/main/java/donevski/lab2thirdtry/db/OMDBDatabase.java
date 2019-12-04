package donevski.lab2thirdtry.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import donevski.lab2thirdtry.models.MovieDetails;

@Database(entities = MovieDetails.class, version = 1, exportSchema = false)
public abstract class OMDBDatabase extends RoomDatabase {

    private static OMDBDatabase database;

    public abstract MovieDetailsDao getMovieDetailsDao();

    public static synchronized OMDBDatabase getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    OMDBDatabase.class, "movie_details_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
