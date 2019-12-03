package donevski.lab2thirdtry.models;

import java.util.List;

public class Movies {
    public List<Movie> movies;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Movie movie:
             movies) {
            sb.append(movie.toString());
        }
        return sb.toString();
    }
}
