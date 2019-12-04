package donevski.lab2thirdtry;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import donevski.lab2thirdtry.models.MovieDetails;
import donevski.lab2thirdtry.viewmodels.MovieDetailsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieDetails movieDetails;
    private MovieDetailsViewModel movieDetailsViewModel;

    private TextView textViewTitle;
    private TextView textViewYear;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initMovieDetails(getIntent().getStringExtra("ImdbID"));
        initViews();
//        setViews();

    }

    public void initViews(){
        textViewTitle = findViewById(R.id.text_view_title);
        textViewYear = findViewById(R.id.text_view_year);
        imageView = findViewById(R.id.image_view_details);
    }

    public void setViews(){
        textViewYear.setText(movieDetails.getYear());
        textViewTitle.setText(movieDetails.getTitle());
        Picasso.get().load(movieDetails.getImageUrl()).into(imageView);
    }

    public void initMovieDetails(String imdbID){
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        // dali ovde se dobiva drug view model so toa i drug repository vo nego i zatoa mi vraka null object za getMovie()

//        movieDetailsViewModel.getAllMovies().observe(this, new Observer<List<MovieDetails>>() {
//            @Override
//            public void onChanged(@Nullable List<MovieDetails> movieDetails) {
//                Logger logger = Logger.getLogger("Movies");
//                logger.info("yes");
//                logger.info(movieDetails.get(0).getTitle());
//                setMovieDetails(movieDetails.get(0));
//                setViews();
//            }
//        });


        //setMovieDetails(movieDetailsViewModel.getMovie(imdbID).getValue());

        movieDetailsViewModel.getMovie(imdbID).observe(this, new Observer<MovieDetails>(){

            @Override
            public void onChanged(@Nullable MovieDetails movieDetails) {
                setMovieDetails(movieDetails);
                setViews();
            }
        });
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }
}
