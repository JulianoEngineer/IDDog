package juliano.oliveira.iddog;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceUtils {

    private ApiServiceUtils() {}
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://iddog-api.now.sh";

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static IDDogService getAPIService() {

        return getClient(BASE_URL).create(IDDogService.class);
    }

    public static void GetDog( String category, String token, final Context ctx, final GridView grd) {

        getAPIService().getCategory(category, token).enqueue(new Callback<GET>() {
            @Override
            public void onResponse(Call<GET> call, Response<GET> response) {
                if (response.isSuccessful()) {

                    GridViewAdapter gridView = new GridViewAdapter(ctx);
                    gridView.setUrls(response.body().getList());
                    grd.setAdapter(gridView);
                    grd.setOnScrollListener(new GridViewScrollListener(ctx));
                }
            }

            @Override
            public void onFailure(Call<GET> call, Throwable t) {

            }
        });
    }
}