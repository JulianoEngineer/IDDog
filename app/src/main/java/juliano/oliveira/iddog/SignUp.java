package juliano.oliveira.iddog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.btn_signup) Button _signup;
    @BindView(R.id.txtEmail) EditText _email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_signup)
    public void submitEmail(View v){

        Pattern patternEmail = Patterns.EMAIL_ADDRESS;

        Matcher matcher = patternEmail.matcher(_email.getText().toString());

        if(_email.getText().toString().isEmpty())
        {
            _email.setError("Campo E-mail deve ser preenchido!");

        }else if(!matcher.matches())
        {
            _email.setError("E-mail inválido!");
        }
    }
}
