package xyz.dev3k.mobile1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

/*Volley es una libreria desarrollada por Google para optimizar el envío de peticiones
Http desde las aplicaciones Android hacia los servidores externos.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText user, pass;
    RequestQueue requestQueue;
    private static final String urlservidor = "http://192.168.169.136/mobile1/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void backToRegister(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
