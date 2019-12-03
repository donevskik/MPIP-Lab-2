package donevski.lab2thirdtry.client;

import android.widget.TextView;
import donevski.lab2thirdtry.models.Movie;
import donevski.lab2thirdtry.models.SearchResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OMDBApiClient {

    private static Retrofit retrofit;

    private static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OMDBApiService getService(){
        return getRetrofit().create(OMDBApiService.class);
    }

    public static void getResults(String s, final TextView textView){
        Call<SearchResults> searchResultsCall = getService().getSearchResults(s);

        searchResultsCall.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if (!response.isSuccessful()){
                    textView.setText("Error code: " + response.code());
                    return;
                }
                SearchResults results = response.body();

                StringBuilder sb = new StringBuilder();
                sb.append("Results: \n");
                if (results.getResults() != null){

                    for (Movie movie:
                            results.getResults()) {
                        sb.append(movie).append("\n\n");
                    }
                }
                else
                    sb.append("Nema takov film\n");
                sb.append("Total results:").append(results.getTotalResults()).append("\n");
                sb.append("Response: ").append(results.isResponse());

                textView.setText(sb.toString());
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
