package pe.edu.utp.yanapaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.database.DB;
import pe.edu.utp.yanapaapp.database.auth.TokenEntity;
import pe.edu.utp.yanapaapp.database.auth.UserEntity;
import pe.edu.utp.yanapaapp.utils.Constants;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvBack, tvPrivacy;
    private EditText txtUsername,txtFullName,txtPassword;
    private Button btnRegister;
    private DB db = new DB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvPrivacy = (TextView) findViewById(R.id.tvPrivacy);
        tvBack = (TextView) findViewById(R.id.tvBack);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtFullName = (EditText) findViewById(R.id.txtFullName);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvBack.setOnClickListener(view -> onBackPressed());

        tvPrivacy.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),PrivacyActivity.class);
            startActivity(i);
        });

        btnRegister.setOnClickListener(view -> {
            if(!TextUtils.isEmpty(txtUsername.getText().toString()) &&
                    !TextUtils.isEmpty(txtPassword.getText().toString()) &&
                    !TextUtils.isEmpty(txtFullName.getText().toString())){
                TokenEntity tokenEntity = new TokenEntity();
                tokenEntity.setAccessToken("xyz");
                tokenEntity.setTokenType("Bearer");
                tokenEntity.setExpiresIn(900);
                tokenEntity.setScope("scope");
                tokenEntity.setJti("xyz-123");
                tokenEntity.setUsername(txtUsername.getText().toString());
                UserEntity userEntity = new UserEntity();
                userEntity.setFullName(txtFullName.getText().toString());
                userEntity.setUsername(txtUsername.getText().toString());
                userEntity.setPassword(txtPassword.getText().toString());
                db.registerOfflineUser(tokenEntity,userEntity);
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                Constants.mUSERNAME = txtUsername.getText().toString();
                startActivity(i);
                finish();
            }else{
                txtUsername.setError("Ingrese un usuario");
                txtPassword.setError("Ingrese un contraseña");
                txtFullName.setError("Ingrese un nombre");
            }
        });
    }
}