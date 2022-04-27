package xyz.dev3k.mobile1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Model model = new Model();
    EditText name, surname, mail, user, pass, pass1, latitud, longitud;

    RequestQueue requestQueue;
    private static final String urlservidor = "http://192.168.169.136/mobile1/insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(this);

        name=(EditText) findViewById(R.id.inputName);
        surname=(EditText) findViewById(R.id.inputSurname);
        mail=(EditText) findViewById(R.id.inputMail);
        user=(EditText) findViewById(R.id.inputUser);
        pass=(EditText) findViewById(R.id.inputPass);
        pass1=(EditText) findViewById(R.id.inputPass2);

        latitud=(EditText) findViewById(R.id.inputLatitud);
        longitud=(EditText) findViewById(R.id.inputLongitud);
    }

    public void registrarUsuario(View view){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                urlservidor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error);
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                if (model.comprobarCorreo(mail.getText().toString())){
                    if (pass == pass1) {
                        params.put("name", name.getText().toString());
                        params.put("surname", surname.getText().toString());
                        params.put("mail", mail.getText().toString());
                        params.put("user", user.getText().toString());
                        String newPass = CifrarPass.md5(pass.getText().toString());
                        params.put("pass", newPass);
                        params.put("latitud", latitud.getText().toString());
                        params.put("longitud", longitud.getText().toString());
                    }
                    else{
                        //Mensaje de que las password no coinciden;
                        System.err.println("Las contraseñas ingresadas no coinciden");
                    }
                }else{
                    System.err.println("El correo electrónico ingresado no es correcto");
                }


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void login(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}