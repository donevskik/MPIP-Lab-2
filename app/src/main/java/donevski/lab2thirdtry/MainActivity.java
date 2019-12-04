package donevski.lab2thirdtry;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import donevski.lab2thirdtry.adapter.MovieAdapter;
import donevski.lab2thirdtry.client.OMDBApiClient;
import donevski.lab2thirdtry.models.MovieDetails;
import donevski.lab2thirdtry.viewmodels.MovieDetailsViewModel;

import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private MovieDetailsViewModel movieDetailsViewModel;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListView();

        updateData();
        textViewResult = findViewById(R.id.text);
    }

    private void initListView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void updateData() {
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);

        movieDetailsViewModel.getAllMovies().observe(this, new Observer<List<MovieDetails>>() {
            @Override
            public void onChanged(@Nullable List<MovieDetails> movieDetails) {
                adapter.updateDataset(movieDetails);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.onActionViewExpanded();

//        Button btnPrev = new Button(this);
//        ((LinearLayout) searchView.getChildAt(0)).addView(btnPrev, searchView.getWidth()*2/3, searchView.getHeight()*2/3);
//


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //call retrofit to search omdbapi with s
                movieDetailsViewModel.setSearchBy(s);
//                updateData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
}
