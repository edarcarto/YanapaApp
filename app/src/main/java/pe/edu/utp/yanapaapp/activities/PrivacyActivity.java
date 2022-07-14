package pe.edu.utp.yanapaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import pe.edu.utp.yanapaapp.R;

public class PrivacyActivity extends AppCompatActivity {
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        back = (TextView) findViewById(R.id.tvBack);
    }

    @Override
    protected void onResume() {
        super.onResume();
        back.setOnClickListener(view -> onBackPressed());
    }
}