package app.arxapp.jonathan.arxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.Vector;


public class Lista_Invitados extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";
    private DatabaseReference invitados;
    String user;
    EditText editNombreInvitado, editCedulaInvitado, editPlacaOpcional, editMazana, editVilla;
    Button buttonAgregarInvitado, verListaInvitados;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lista_invitados);


        editNombreInvitado = (EditText) findViewById(R.id.txtNombreInvitado);
        editCedulaInvitado = (EditText) findViewById(R.id.txtCedulaInvitado);
        editPlacaOpcional = (EditText) findViewById(R.id.txtPlaca);
        editMazana = (EditText) findViewById(R.id.txtManzana);
        editVilla = (EditText) findViewById(R.id.txtVila);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getUid();
        invitados = FirebaseDatabase.getInstance().getReference("arxappDataBase");

        buttonAgregarInvitado = (Button) findViewById(R.id.btnAgregarInvitado);
        buttonAgregarInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarInvitado();
            }
        });



        verListaInvitados = (Button) findViewById(R.id.btnVerLista);
        verListaInvitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                invitados.child("ListaInvitados"+user).child("Invitados").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Vector<String> jsonInvitados = new Vector<>();
                        Vector<Invitado> vectorInvitados = new Vector<>();
                        int contador =0;
                        //Lista_Invitados invitadoTmp = dataSnapshot.getValue(Lista_Invitados.class);
                        if(dataSnapshot.getValue() != null){
                            //Toast.makeText(Lista_Invitados.this, "d" , Toast.LENGTH_SHORT).show();
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                Invitado invitadoTmp = new Invitado();
                                for (DataSnapshot subChild : child.getChildren()){

                                    jsonInvitados.add(subChild.getValue().toString());

                                    Log.w("json", jsonInvitados.get(contador));
                                    contador ++;

                                }
                                invitadoTmp.setInvitadoId(jsonInvitados.get(3));
                                invitadoTmp.setNombreInvitado(jsonInvitados.get(5));
                                invitadoTmp.setCedulaInvitado(jsonInvitados.get(0));
                                invitadoTmp.setPlaca(jsonInvitados.get(6));
                                invitadoTmp.setManzana(jsonInvitados.get(4));
                                invitadoTmp.setVilla(jsonInvitados.get(7));
                                invitadoTmp.setIdUsuario(jsonInvitados.get(2));
                                invitadoTmp.setEstado(Boolean.valueOf(jsonInvitados.get(1)));
                                vectorInvitados.add(invitadoTmp);
                                jsonInvitados.clear();
                                contador=0;
                            }





                        }

                        for (Invitado invitado : vectorInvitados){
                            Log.w("InvitadorJson", invitado.getInvitadoId());
                            Log.w("InvitadorJson", invitado.getNombreInvitado());
                            Log.w("InvitadorJson", invitado.getCedulaInvitado());
                            Log.w("InvitadorJson", invitado.getPlaca());
                            Log.w("InvitadorJson", invitado.getManzana());
                            Log.w("InvitadorJson", invitado.getVilla());
                            Log.w("InvitadorJson", invitado.getIdUsuario());
                            Log.w("InvitadorJson", String.valueOf(invitado.isEstado()));


                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Intent intentListaInvitados = new Intent(v.getContext(), activity_administracion_ciudadela_lista.class);
                startActivityForResult(intentListaInvitados, 0);


            }


        });


    }

    public void registrarInvitado() {

        String tmpNombreInvitado = editNombreInvitado.getText().toString();
        String tmpCedulaInvitado = editCedulaInvitado.getText().toString();
        String tmpPlaca = editPlacaOpcional.getText().toString();
        String tmpManzana = editMazana.getText().toString();
        String tmpVilla = editVilla.getText().toString();

        if(!TextUtils.isEmpty(tmpNombreInvitado)&
                !TextUtils.isEmpty(tmpCedulaInvitado)&!TextUtils.isEmpty(tmpManzana)&
                !TextUtils.isEmpty(tmpVilla)){
            String tmpIdInvitado = invitados.push().getKey();
            //Toast.makeText(Lista_Invitados.this,tmpIdInvitado, Toast.LENGTH_SHORT).show();
            Invitado tmpInvitado = new Invitado(tmpIdInvitado,tmpNombreInvitado, tmpCedulaInvitado,tmpPlaca,tmpManzana,tmpVilla, user, false);
            invitados.child("ListaInvitados"+user).child("Invitados").child(tmpIdInvitado).setValue(tmpInvitado);


        }else {
            Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }



}