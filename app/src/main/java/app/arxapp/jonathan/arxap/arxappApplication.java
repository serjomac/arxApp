package app.arxapp.jonathan.arxap;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class arxappApplication extends Application {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "Registro";
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Log.w("Arxapp Aplicaction" ,"Usurio Logeado: " + firebaseUser.getEmail() );
                }else {
                    Log.w("Arxapp Aplicaction" , "Usurio NO Logeado");
                }
            }
        };
    }
}
