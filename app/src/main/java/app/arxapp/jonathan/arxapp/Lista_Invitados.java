package app.arxapp.jonathan.arxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Lista_Invitados extends AppCompatActivity {

    private  Button btnVerLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_invitados);

        btnVerLista = (Button) findViewById(R.id.btnVerLista);
        btnVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iniciarSesion();
                Intent intent2 = new Intent(v.getContext(), activity_administracion_ciudadela_lista.class);
                startActivityForResult(intent2,0);
            }
        });


    }
}
