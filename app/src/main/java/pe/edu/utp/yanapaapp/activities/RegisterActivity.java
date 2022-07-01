package pe.edu.utp.yanapaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import pe.edu.utp.yanapaapp.R;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvBack, tvPrivacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvPrivacy = (TextView) findViewById(R.id.tvPrivacy);
        tvBack = (TextView) findViewById(R.id.tvBack);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvBack.setOnClickListener(view -> onBackPressed());

        tvPrivacy.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),PrivacyActivity.class);
            startActivity(i);
        });
    }
}