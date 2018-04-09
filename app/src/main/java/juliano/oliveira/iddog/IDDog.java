package juliano.oliveira.iddog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IDDog extends AppCompatActivity {

    private Button _signup;
    private EditText _email;
    private CheckBox _connected;
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
        _connected = (CheckBox) findViewById(R.id.chb_stay_connected);

        _apiService = ApiServiceUtils.getAPIService();

        _progressDialog = new ProgressDialog(this,R.style.ProgressTheme);
        _progressDialog.setIndeterminate(true);
        _progressDialog.setMessage(getString(R.string.auth_wait_message));

        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern patternEmail = Patterns.EMAIL_ADDRESS;

                Matcher matcher = patternEmail.matcher(_email.getText().toString());

                if(_email.getText().toString().isEmpty())
                {
                    _email.setError(getString(R.string.email_empty_error));

                }else if(!matcher.matches())
                {
                    _email.setError(getString(R.string.email_invalid_error));

                }else{
                    _progressDialog.show();
                    String email = _email.getText().toString();
                    sendAuth(email, _apiService);
                }
            }
        });

    }

    public void sendAuth(String email, IDDogService api) {

        api.signup(email).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    Log.i("SIGNUP", getString(R.string.signin_sucess_message) + response.body().getUser().getToken());
                    _token = response.body().getUser().getToken();
                    if(_token.length()>0) {
                        if (_connected.isChecked())

                        dogView(_token);
                    }
                  }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("SIGNUP", getString(R.string.signin_error_message));
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
