package app.arxapp.jonathan.arxapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    String invitadoPorSesion;
    private static final String TAG = "ContainerActivity";
    private DatabaseReference invitados;
    String user;
    EditText editNombreInvitado, editCedulaInvitado, editPlacaOpcional, editMazana, editVilla;
    Button buttonAgregarInvitado, verListaInvitados;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

                                    //Log.w("json", tokenInvitado);
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


                        //JSONObject json = invitadosData.getJSONObject(tokenInvitado);

                        //Toast.makeText(Lista_Invitados.this, jsonInvitados , Toast.LENGTH_SHORT).show();
                        /*
                        try {
                            //JSONObject jsnobject = new JSONObject(jsonInvitados);
                            //JSONArray jsonArray = new JSONArray(jsonInvitados);

                            //String idInvitadoTmp = invitados.child("ListaInvitados"+user).child("Invitados").child().getKey();
                            //Toast.makeText(Lista_Invitados.this, idInvitadoTmp , Toast.LENGTH_SHORT).show();
                            //JSONObject json = invitadosData.getJSONObject("ListaInvitados"+user).getJSONObject("Invitados");
                           // JSONObject json = new JSONObject(invitadosData.toString());
                            //Toast.makeText(Lista_Invitados.this, "Jalo!" , Toast.LENGTH_SHORT).show();
                            //JSONObject mJsonObject = mJsonArray.getJSONObject(0);

                           //String nombre = json.getString("manzana");
                            String manzanaTmp = "";
                            for (int i =0; i < jsonArray.length();i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                if (obj.has("manzana")) {
                                    manzanaTmp = obj.getString("manzana");

                                }
                                //Invitado invitadoTmp = new Invitado(invitadosData.getString("manzana"));
                            }


                            Toast.makeText(Lista_Invitados.this,  manzanaTmp  , Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Toast.makeText(Lista_Invitados.this, e.toString() , Toast.LENGTH_SHORT).show();
                        }

                    */


                        //jsonInvitados = gson.toJson(Invitado.class);
                        //gson.toJson(Invitado.class);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
