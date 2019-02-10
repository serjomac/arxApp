package app.arxapp.jonathan.arxap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Politicas extends AppCompatActivity {
    TextView textPolitica;
    ImageView botonRegresar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas);
        textPolitica = (TextView)findViewById(R.id.txtPolitica);
        textPolitica.setMovementMethod(new ScrollingMovementMethod());

        botonRegresar = (ImageView) (findViewById(R.id.regresar));
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Menu_Residente.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }
}
