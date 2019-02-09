package app.arxapp.jonathan.arxap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Incicar_Sesion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnIngresar2 = (Button) findViewById(R.id.btnIniciar2);
        btnIngresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(v.getContext(), activity_administracion_ciudadela.class);
                startActivityForResult(intent2,0);
            }
        });
    }
}
