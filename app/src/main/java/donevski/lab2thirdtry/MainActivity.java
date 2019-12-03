package donevski.lab2thirdtry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import donevski.lab2thirdtry.client.OMDBApiClient;
import donevski.lab2thirdtry.client.OMDBApiService;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text);
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
                OMDBApiClient.getResults(s, textViewResult);
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
