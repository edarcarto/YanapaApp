package pe.edu.utp.yanapaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.database.DB;
import pe.edu.utp.yanapaapp.database.auth.TokenEntity;
import pe.edu.utp.yanapaapp.database.auth.UserEntity;
import pe.edu.utp.yanapaapp.net.ConfigRetrofit;
import pe.edu.utp.yanapaapp.net.ServicesRetrofit;
import pe.edu.utp.yanapaapp.net.response.Token;
import pe.edu.utp.yanapaapp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;

public class SingInActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etUsername, etPassword;
    private TextView tvForgotPassword, tvPrivacy, tvRegister;
    private DB db = new DB();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        tvPrivacy = (TextView) findViewById(R.id.tvPrivacy);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        if(db.getToken() != null){
            TokenEntity te = db.getToken();
            Constants.mUSERNAME = te.getUsername();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvForgotPassword.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
            startActivity(i);
        });
        tvPrivacy.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),PrivacyActivity.class);
            startActivity(i);
        });
        tvRegister.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
            startActivity(i);
        });
        btnLogin.setOnClickListener(view -> {
            if(!TextUtils.isEmpty(etUsername.getText().toString()) &&
                    !TextUtils.isEmpty(etPassword.getText().toString())) {
                try {
                    Interceptor headerAuthorizationInterceptor = chain -> {
                        String authToken = Credentials.basic(Constants.USERNAME,Constants.PASSWORD);
                        Request request = chain.request();
                        request = request.newBuilder().header("Authorization",authToken).build();
                        return chain.proceed(request);
                    };
                    //clientBuilder.addInterceptor(headerAuthorizationInterceptor);
                    OkHttpClient clientBuilder = new OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor).build();
                    Gson gson = new GsonBuilder().setLenient().create();
                    ServicesRetrofit services = ConfigRetrofit.getConfigBasicAuth(clientBuilder,gson)
                            .create(ServicesRetrofit.class);
                    RequestBody rbType = RequestBody.create("password",MediaType.parse("text/plain"));
                    RequestBody rbUsername = RequestBody.create(etUsername.getText().toString(),MediaType.parse("text/plain"));
                    RequestBody rbPassword = RequestBody.create(etPassword.getText().toString(),MediaType.parse("text/plain"));
                    Call<Token> token = services.postToken(
                            rbType,
                            rbUsername,
                            rbPassword
                    );
                    token.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(@NonNull Call<Token> call, @NonNull retrofit2.Response<Token> response) {
                            assert response.body() != null;
                            Log.d("RESPONSE",response.body().getAccess_token());
                            TokenEntity tokenEntity = new TokenEntity();
                            tokenEntity.setAccessToken(response.body().getAccess_token());
                            tokenEntity.setTokenType(response.body().getToken_type());
                            tokenEntity.setExpiresIn(response.body().getExpires_in());
                            tokenEntity.setScope(response.body().getScope());
                            tokenEntity.setJti(response.body().getJti());
                            tokenEntity.setUsername(etUsername.getText().toString());
                            UserEntity user = new UserEntity();
                            user.setUsername(etUsername.getText().toString());
                            String name  = (etUsername.getText().toString().equals("Efren")) ? "Efren Carrillo" : "Everth Pintado";
                            user.setFullName(name);
                            user.setPassword(etPassword.getText().toString());
                            db.registerOfflineUser(tokenEntity,user);
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                            Log.d("enqueue",t.getMessage());
                        }
                    });
                }catch (Exception e){
                    Log.d("ERROR",e.getMessage());
                }
            }else{
                etUsername.setError("El campo esta vacio");
                etPassword.setError("El campo esta vacio");
            }
        });
    }
}
