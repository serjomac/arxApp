package app.arxapp.jonathan.arxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private  Button btnIngresar2;
    private  Button btnRegistrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar2 = (Button) findViewById(R.id.btnIniciar2);
        btnIngresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(v.getContext(), Menu_Residente.class);
                startActivityForResult(intent2,0);
            }
        });


    }
}
