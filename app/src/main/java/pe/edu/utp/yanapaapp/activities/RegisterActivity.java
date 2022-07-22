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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.database.DB;
import pe.edu.utp.yanapaapp.database.auth.UserEntity;
import pe.edu.utp.yanapaapp.models.User;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvBack, tvPrivacy;
    private EditText txtUsername,txtFullName,txtPassword;
    private Button btnRegister;
    private DB db = new DB();
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
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
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
                mAuth.createUserWithEmailAndPassword(
                        txtUsername.getText().toString(),
                                txtPassword.getText().toString()
                        )
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                User u = new User(
                                        txtFullName.getText().toString(),
                                        txtUsername.getText().toString(),
                                        "Colaborador");
                                assert user != null;
                                mDatabase.child("users").child(user.getUid()).setValue(u);
                                // offline
                                UserEntity userEntity = new UserEntity();
                                userEntity.setFullName(txtFullName.getText().toString());
                                userEntity.setUsername(txtUsername.getText().toString());
                                userEntity.setPassword(txtPassword.getText().toString());
                                userEntity.setRole("Colaborador");
                                db.registerOfflineUser(userEntity);
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        });
                /*
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
                */
            }else{
                txtUsername.setError("Ingrese un usuario");
                txtPassword.setError("Ingrese un contraseña");
                txtFullName.setError("Ingrese un nombre");
            }
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }*/
}