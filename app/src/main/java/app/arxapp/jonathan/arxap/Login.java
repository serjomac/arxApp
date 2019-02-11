package app.arxapp.jonathan.arxap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;

public class Login extends AppCompatActivity {
    private static final String TAG = "login_activity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private  Button btnIngresar2;
    private EditText editLoginCorreo, editLoginClave;
    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;
    private TextView btnRegistro;
    private String correo="";
    private String clave = "" ;
    private DatabaseReference referenceUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLoginCorreo = (EditText) findViewById(R.id.txtLoginUsuario);
        editLoginClave = (EditText) findViewById(R.id.txtLoginClave);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    //Toast.makeText(Login.this,"Loggueado: " + firebaseUser.getUid(), Toast.LENGTH_LONG).show();
                    Log.w(TAG, "Usurio Logeado: " + firebaseUser.getEmail());
                    goHome();
                }else {
                    Log.w(TAG, "Usurio NO Logeado");
                }
            }
        };
        btnRegistro = (TextView) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                crearCuenta();
                //registrarUsuarioABaseDeDatosFirebase();
            }
        });

        btnIngresar2 = (Button) findViewById(R.id.btnIniciar2);
        btnIngresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(esAdministracion()){
                    //Log.w("validacionGarita", "si es garita");
                    Intent intentAdministracion = new Intent(Login.this, activity_administracion_ciudadela_lista.class);
                    startActivity(intentAdministracion);
                }else {
                    iniciarSesion();
                }

                //Intent intent2 = new Intent(v.getContext(), activity_administracion_ciudadela.class);
                //startActivityForResult(intent2,0);
            }
        });

        loginButtonFacebook = (LoginButton) findViewById(R.id.login_facebook);
        loginButtonFacebook.setReadPermissions(Arrays.asList("email"));
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Log.w(TAG,"Facebook Login Correcto token: " + loginResult.getAccessToken().getApplicationId());
                signInFabookFirebase(loginResult.getAccessToken());
                goHome();
            }


            @Override
            public void onCancel() {
                Log.w(TAG, "Facebook Login Cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w("Login Facebook", "Facebook Login error: " + error.toString());



            }
        });
    }

    private void signInFabookFirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    goHome();
                }else {
                    Log.w("Login Facebook", "No se pudo ingresar");
                }
            }
        });
    }

    public void goHome() {

        try {
            int pos = correo.indexOf("@");
            String usuairo = correo.substring(0, pos);
            Intent intent = new Intent(this, Menu_Residente.class);
            intent.putExtra(Menu_Residente.usuarioLoggeado, usuairo);
            startActivity(intent);
        } catch (Exception e){
            Intent intent = new Intent(this, Menu_Residente.class);
            FirebaseUser fireBaseUser = firebaseAuth.getCurrentUser();
            String usuarioLogeado = fireBaseUser.getEmail();
            int pos = usuarioLogeado.indexOf("@");
            String usuairo = usuarioLogeado.substring(0,pos);

            intent.putExtra(Menu_Residente.usuarioLoggeado, usuairo);
            startActivity(intent);
        }



    }



    /*

    public void registrarUsuarioABaseDeDatosFirebase() {

        String tmpCorreoUsuario = correo;
        String tmpClaveUsuario = clave;
        String tmpIdUsuario = firebaseAuth.getCurrentUser().toString();
        String tmpManzana = "";
        String tmpVilla = "";
        String tmpNombre = "";



        if(!TextUtils.isEmpty(tmpNombreInvitado)&
                !TextUtils.isEmpty(tmpCedulaInvitado)&!TextUtils.isEmpty(tmpManzana)&
                !TextUtils.isEmpty(tmpVilla)){
            String tmpIdInvitado = invitados.push().getKey();
            //Toast.makeText(Lista_Invitados.this,tmpIdInvitado, Toast.LENGTH_SHORT).show();
            Invitado tmpInvitado = new Invitado(tmpIdInvitado,tmpNombreInvitado, tmpCedulaInvitado,tmpPlaca,tmpManzana,tmpVilla, usuarioLogueado, false);
            invitados.child("ListaInvitados"+usuarioLogueado).child("Invitados").child(tmpIdInvitado).setValue(tmpInvitado);


        }else {
            Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }


        referenceUsuario = FirebaseDatabase.getInstance().getReference("arxappDataBase");
        Usuario usuarioTmp = new Usuario(tmpIdUsuario,tmpCorreoUsuario, tmpClaveUsuario,tmpManzana,tmpVilla, tmpNombre);
        referenceUsuario.setValue(usuarioTmp);
    }
    */



    public boolean esAdministracion(){
        boolean estado = true;
        if (editLoginCorreo.getText().toString().equals("admin@gmail.com") &&
                editLoginClave.getText().toString().equals("admin1234")){
           // Log.w("validacionGarita", "si es garita");
            //Log.w("validacionGarita", firebaseAuth.toString());
            return estado;
        }else {
            estado = false;
            //Log.w("validacionGarita", "----" +  editLoginCorreo.getText().toString());
            return estado;
        }

    }

    public void iniciarSesion(){

        correo = editLoginCorreo.getText().toString();
        clave = editLoginClave.getText().toString();

        if(TextUtils.isEmpty(correo) && TextUtils.isEmpty(clave)){
            Toast.makeText(Login.this, "Debe ingresar un correo y una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(Login.this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(clave)){
            Toast.makeText(Login.this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(correo,clave).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            goHome();
                        }else {
                            if (task.getException() instanceof FirebaseAuthActionCodeException) {
                                Toast.makeText(Login.this, "Usuario No valido", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Login.this, "Usuario no Existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    public void crearCuenta(){
        correo = editLoginCorreo.getText().toString();
        clave = editLoginClave.getText().toString();

        if(TextUtils.isEmpty(correo) && TextUtils.isEmpty(clave)){
            Toast.makeText(Login.this, "Debe ingresar un correo y una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(Login.this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(clave)){
            Toast.makeText(Login.this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(correo,clave).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(Login.this, "cuenta creada", Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Login.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Login.this, "Falló la creacón revise el correo o el número de dígitos de la contraseña", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
