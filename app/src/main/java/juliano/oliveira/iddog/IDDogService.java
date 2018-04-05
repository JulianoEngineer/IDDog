package juliano.oliveira.iddog;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDDogService {


    @FormUrlEncoded
    @POST("/signup")
    Call<Post> signup(@Field("email") String email);

    @GET("/feed")
    Call<juliano.oliveira.iddog.GET> getCategory(@Query("category") String cat, @Header("Authorization") String token);

}
