package donevski.lab2thirdtry.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import donevski.lab2thirdtry.R;
import donevski.lab2thirdtry.models.Movie;
import donevski.lab2thirdtry.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<MovieDetails> movies = new ArrayList<>();
    private View.OnClickListener listener;

    public MovieAdapter(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item, viewGroup, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        MovieDetails movieDetails = movies.get(i);
        movieHolder.textViewTitle.setText(movieDetails.getTitle());
        movieHolder.textViewYear.setText(movieDetails.getYear());
        Picasso.get().load(movieDetails.getImageUrl()).into(movieHolder.imageView);

        movieHolder.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateDataset(List<MovieDetails> movies){
        this.movies=movies;
        notifyDataSetChanged();
    }

    public String getClickedItemID(MovieHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return movies.get(adapterPosition).getImdbID();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewYear;
        private ImageView imageView;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewYear = itemView.findViewById(R.id.text_view_year);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setTag(this);
        }

        public void setOnClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }
    }
}
