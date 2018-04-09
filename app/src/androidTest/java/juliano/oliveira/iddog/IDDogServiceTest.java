package juliano.oliveira.iddog;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

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
        Response<Post> response = mAPIService.signup("email@email.com").execute();

        Assert.assertEquals(200, response.code());
    }

    @Test
    public void GET_FEED_TEST() throws Exception{

        Response<Post> response = mAPIService.signup("email@email.com").execute();

        Assert.assertEquals(200, response.code());

        Response<GET> responseg = mAPIService.getCategory("husky",response.body().getUser().getToken()).execute();

        Assert.assertEquals(200, responseg.code());
    }

    public void sendPost(String email) {

        mAPIService.signup(email).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    Log.i("SIGNUP", "post submitted to API. Token=" + response.body().getUser().getToken());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("SIGNUP", "Unable to submit post to API.");
            }
        });
    }

}
