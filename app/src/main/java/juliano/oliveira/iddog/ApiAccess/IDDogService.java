package juliano.oliveira.iddog.ApiAccess;

import juliano.oliveira.iddog.ApiAccess.AuthPost;
import juliano.oliveira.iddog.ApiAccess.FeedGet;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IDDogService {


    @FormUrlEncoded
    @POST("/signup")
    Call<AuthPost> signup(@Field("email") String email);

    @GET("/feed")
    Call<FeedGet> getCategory(@Query("category") String cat, @Header("Authorization") String token);

}
