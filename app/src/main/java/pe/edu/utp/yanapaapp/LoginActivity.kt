package pe.edu.utp.yanapaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java);
            startActivity(intent);
        }
    }
}