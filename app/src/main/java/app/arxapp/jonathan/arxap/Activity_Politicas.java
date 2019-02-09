package app.arxapp.jonathan.arxap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Activity_Politicas extends AppCompatActivity {
    TextView textPolitica;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas);
        textPolitica = (TextView)findViewById(R.id.txtPolitica);
        textPolitica.setMovementMethod(new ScrollingMovementMethod());
    }
}
