package pe.edu.utp.yanapaapp.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.database.DB;
import pe.edu.utp.yanapaapp.database.auth.UserEntity;

public class UserProfileActivity extends AppCompatActivity{
    TextView fullNameField,usernameField,donationLabel,paymentLabel;
    TextInputEditText txtFullName, txtEmail, txtPassword;
    Button btnUpdate;
    private DB db = new DB();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        fullNameField = (TextView) findViewById(R.id.fullNameField);
        usernameField = (TextView) findViewById(R.id.usernameField);
        donationLabel = (TextView) findViewById(R.id.donationLabel);
        paymentLabel = (TextView) findViewById(R.id.paymentLabel);
        txtFullName = (TextInputEditText) findViewById(R.id.txtFullName);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        setInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnUpdate.setOnClickListener(view -> {
            if(!TextUtils.isEmpty(txtFullName.getText()) &&
            !TextUtils.isEmpty(txtEmail.getText()) &&
            !TextUtils.isEmpty(txtPassword.getText())){
                UserEntity userEntity = db.getUser(db.getToken().getUsername());
                userEntity.setUsername(db.getToken().getUsername());
                userEntity.setFullName(String.valueOf(txtFullName.getText()));
                userEntity.setPassword(String.valueOf(txtPassword.getText()));
                db.updateUser(userEntity);
                Toast toast = new Toast(getApplicationContext());
                toast.setText("Se actualizó el usuario");
                toast.show();
            }else{
                txtFullName.setError("Ingrese su nombre");
                txtEmail.setError("Ingrese su usuario");
                txtPassword.setError("Ingrese su password");
            }
        });
    }

    public void setInfo(){
        UserEntity user = db.getUser(db.getToken().getUsername());
        fullNameField.setText(user.getFullName());
        usernameField.setText(user.getUsername());
        txtEmail.setText(user.getUsername());
        txtFullName.setText(user.getFullName());
    }
}
