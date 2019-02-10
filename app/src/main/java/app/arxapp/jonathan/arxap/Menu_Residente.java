package app.arxapp.jonathan.arxap;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import static android.graphics.Color.RED;

public class Menu_Residente extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";
    private ImageView botonListaInvitados, botonNoticias, botonReservaClub, botonPagoAlicuota;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public static final String usuarioLoggeado = "name";
    public static  String tokenUsuarioLoggeado = "idUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_residente);

        final String user = getIntent().getStringExtra("name");
        setTitle(user);
        inicializarFirebase();
        botonListaInvitados = (ImageView) (findViewById(R.id.imgListaInvitados));
        botonListaInvitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Menu_Residente.this,tokenUsuarioLoggeado, Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(v.getContext(), Lista_Invitados.class);
                startActivityForResult(intent1, 0);
                finish();
            }
        });

        botonNoticias = (ImageView) (findViewById(R.id.imgNotificaciones));
        botonNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), Noticias.class);
                startActivityForResult(intent2, 0);
                finish();
            }
        });

        botonReservaClub = (ImageView) (findViewById(R.id.imgReservacionClub));
        botonReservaClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(v.getContext(), Reserva_club.class);
                startActivityForResult(intent3, 0);
                finish();
            }
        });

        botonPagoAlicuota = (ImageView) (findViewById(R.id.imgPagoAlicuota));
        botonPagoAlicuota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(v.getContext(), Reserva_club.class);
                startActivityForResult(intent4, 0);
                finish();
            }
        });
    }



    private void inicializarFirebase(){


        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Toast.makeText(Menu_Residente.this,"Log", Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.perfil:
                Intent i1 = new Intent(Menu_Residente.this,perfilResidente.class);
                startActivity(i1);
                finish();
                break;

            case R.id.cerrarSesion:
                firebaseAuth.signOut();
                if(LoginManager.getInstance() != null){
                    LoginManager.getInstance().logOut();
                }
                Toast.makeText(this,"Se cerro la sesion",Toast.LENGTH_SHORT);
                Intent i = new Intent(Menu_Residente.this, Login.class);
                startActivity(i);
                finish();
                break;

            case R.id.politicas:
                Intent intentPoliticas = new Intent(Menu_Residente.this,Activity_Politicas.class);
                startActivity(intentPoliticas);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
