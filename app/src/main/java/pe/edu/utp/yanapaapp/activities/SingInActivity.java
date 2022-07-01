package pe.edu.utp.yanapaapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingInActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etEmail, etPassword;
    private TextView tvForgotPassword, tvPrivacy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        tvPrivacy = (TextView) findViewById(R.id.tvPrivacy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etEmail.getText().toString()) &&
                        !TextUtils.isEmpty(etPassword.getText().toString())) {
                    try {

                    }catch (Exception e){

                    }
                }else{
                    etEmail.setError("El campo está vacío");
                    etPassword.setError("El campo está vacío");
                }
            }
        });
    }
}
