package app.arxapp.jonathan.arxapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "Registro";
    private Button btnRegistro;
    private EditText editClave, editCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnRegistro = (Button) findViewById(R.id.btnIniciar2);
        editCorreo= (EditText) findViewById(R.id.txtCorreo);
        editClave = (EditText)  findViewById(R.id.txtClave);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Log.w(TAG, "Usurio Logeado: " + firebaseUser.getEmail());
                }else {
                    Log.w(TAG, "Usurio NO Logeado");
                }
            }
        };

        btnRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                crearCuenta();
            }
        });
    }


    public void crearCuenta(){
        String correo = editCorreo.getText().toString();
        String clave = editClave.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(correo,clave).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(Registro.this, "cuenta creada", Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Registro.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Registro.this, "No se creo la cuenta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }



    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
