package pe.edu.utp.yanapaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import pe.edu.utp.yanapaapp.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextView tvPrivacy, tvBack;
    private EditText txtEmail;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        tvPrivacy = (TextView) findViewById(R.id.tvPrivacy);
        txtEmail = findViewById(R.id.txtEmail);
        tvBack = (TextView) findViewById(R.id.tvBack);
        btnLogin = findViewById(R.id.btnLogin);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvBack.setOnClickListener(view -> onBackPressed());

        tvPrivacy.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),PrivacyActivity.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener(view -> {
            if(!TextUtils.isEmpty(txtEmail.getText().toString())){
                mAuth.sendPasswordResetEmail(txtEmail.getText().toString())
                        .addOnCompleteListener(task -> {
                           if(task.isSuccessful()){
                               Toast.makeText(ForgotPasswordActivity.this, "Se ha enviado un correo para resetear la contraseña",
                                       Toast.LENGTH_SHORT).show();
                           }else{
                               Log.w("TAG", "createUserWithEmail:failure", task.getException());
                               Toast.makeText(ForgotPasswordActivity.this, "El correo no existe en nuestra bd.",
                                       Toast.LENGTH_SHORT).show();
                           }
                        });
            }else{
                txtEmail.setError("Ingrese su correo electronico");
            }
        });
    }
}