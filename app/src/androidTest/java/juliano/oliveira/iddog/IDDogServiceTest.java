package juliano.oliveira.iddog;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import juliano.oliveira.iddog.ApiAccess.ApiServiceUtils;
import juliano.oliveira.iddog.ApiAccess.AuthPost;
import juliano.oliveira.iddog.ApiAccess.FeedGet;
import juliano.oliveira.iddog.ApiAccess.IDDogService;
import retrofit2.Callback;
import retrofit2.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Retrofit;



@RunWith(AndroidJUnit4.class)
public class IDDogServiceTest {

    Retrofit _retrofit;
    IDDogService _idDogService;
    IDDogService mAPIService;

    @Before
    public void SETUP()
    {
        _retrofit = ApiServiceUtils.getClient("https://iddog-api.now.sh");

       // _idDogService = _retrofit.create(IDDogService.class);
        mAPIService = ApiServiceUtils.getAPIService();
    }

    @Test
    public void SIGNUP_TEST() throws Exception{

        //sendAuth("example");
        Response<AuthPost> response = mAPIService.signup("email@email.com").execute();

        Assert.assertEquals(200, response.code());
    }

    @Test
    public void GET_FEED_TEST() throws Exception{

        Response<AuthPost> response = mAPIService.signup("email@email.com").execute();

        Assert.assertEquals(200, response.code());

        Response<FeedGet> responseg = mAPIService.getCategory("husky",response.body().getUser().getToken()).execute();

        Assert.assertEquals(200, responseg.code());
    }

    public void sendPost(String email) {

        mAPIService.signup(email).enqueue(new Callback<AuthPost>() {
            @Override
            public void onResponse(Call<AuthPost> call, Response<AuthPost> response) {

                if(response.isSuccessful()) {
                    Log.i("SIGNUP", "post submitted to API. Token=" + response.body().getUser().getToken());
                }
            }

            @Override
            public void onFailure(Call<AuthPost> call, Throwable t) {
                Log.e("SIGNUP", "Unable to submit post to API.");
            }
        });
    }

}
