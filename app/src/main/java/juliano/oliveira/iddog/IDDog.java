package juliano.oliveira.iddog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IDDog extends AppCompatActivity {

    private Button _signup;
    private EditText _email;
    private Retrofit _retrofit;
    private IDDogService _apiService;
    private String _token;
    private ProgressDialog _progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        _signup = (Button) findViewById(R.id.btn_signup);
        _email = (EditText) findViewById(R.id.txt_Email);

        _retrofit = RetrofitClient.getClient("https://iddog-api.now.sh");
        _apiService = ApiServiceUtils.getAPIService();

        _progressDialog = new ProgressDialog(this);
        _progressDialog.setIndeterminate(true);
        _progressDialog.setMessage("Authenticating...");

        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern patternEmail = Patterns.EMAIL_ADDRESS;

                Matcher matcher = patternEmail.matcher(_email.getText().toString());

                if(_email.getText().toString().isEmpty())
                {
                    _email.setError("Campo E-mail deve ser preenchido!");

                }else if(!matcher.matches())
                {
                    _email.setError("E-mail inválido!");

                }else{
                    _progressDialog.show();
                    String email = _email.getText().toString();
                    sendPost(email, _apiService);
                }
            }
        });

    }

    private boolean authSync(String email)
    {
        Response<Post> response;

        try {
            response = _apiService.signup(email).execute();

            if(response.code() == 200)
            {
                _token = response.body().getUser().getToken();
                return true;

            }else{

                Toast.makeText(this, "Problems on Auth Method. Error code:"+response.code() ,Toast.LENGTH_LONG).show();
                return false;

            }
        }catch (IOException e)
        {
            Toast.makeText(this, "Problems on Auth Method",Toast.LENGTH_LONG).show();
            return false;
        }

    }


    public void sendPost(String email, IDDogService api) {

        api.signup(email).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    Log.i("SIGNUP", "post submitted to API. Token=" + response.body().getUser().getToken());
                    _token = response.body().getUser().getToken();
                    if(_token.length()>0) {
                        dogView(_token);
                    }
                  }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("SIGNUP", "Unable to submit post to API.");
            }
        });
    }

    private void dogView(String token)
    {
        Intent intent = new Intent(this,IDDogViewer.class );
        intent.putExtra("TOKEN", token);
        _progressDialog.dismiss();
        startActivity(intent);
    }
}
