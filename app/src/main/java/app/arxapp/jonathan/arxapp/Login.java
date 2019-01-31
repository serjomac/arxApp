package app.arxapp.jonathan.arxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Login extends AppCompatActivity {
    private static final String TAG = "login_activity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private  Button btnIngresar2;
    private EditText editLoginCorreo, editLoginClave;
    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;
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
                    Log.w("prueba", "///////////////////////////////////////////");
                    Log.w(TAG, "Usurio Logeado: " + firebaseUser.getEmail());
                    goHome();
                }else {
                    Log.w(TAG, "Usurio NO Logeado");
                }
            }
        };

        btnIngresar2 = (Button) findViewById(R.id.btnIniciar2);
        btnIngresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
                //Intent intent2 = new Intent(v.getContext(), Menu_Residente.class);
                //startActivityForResult(intent2,0);
            }
        });

        loginButtonFacebook = (LoginButton) findViewById(R.id.login_facebook);
        loginButtonFacebook.setReadPermissions(Arrays.asList("email"));
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w(TAG,"Facebook Login Correcto token: " + loginResult.getAccessToken().getApplicationId());
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

                Intent intent2 = new Intent(v.getContext(), activity_administracion_ciudadela.class);
                startActivityForResult(intent2,0);

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

    public void goHome(){
        Intent intent = new Intent(this, Menu_Residente.class);
        startActivity(intent);
    }


    public void iniciarSesion(){

        String correo = editLoginCorreo.getText().toString();
        String clave = editLoginClave.getText().toString();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
