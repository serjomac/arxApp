package app.arxapp.jonathan.arxap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Lista_Invitados extends AppCompatActivity implements Serializable {
    Lista_Invitados_Generada lista_invitados_generada;
    ArrayList<Invitado> vectorInvitados;
    //ListView listViewInvitados;
    //Adaptador_Item_Invitados adaptador_item_invitados;
    //String invitadoPorSesion;
    private static final String TAG = "ContainerActivity";
    private DatabaseReference invitados;
    String usuarioLogueado;
    String emailUsuarioLogueado;
    EditText editNombreInvitado, editCedulaInvitado, editPlacaOpcional, editMazana, editVilla, editEmailInvitado;
    Button buttonAgregarInvitado, verListaInvitados;
    private FirebaseAuth firebaseAuth;

    public Lista_Invitados() {
        lista_invitados_generada = new Lista_Invitados_Generada(this);
    }

    ImageView botonRegresar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_invitados);


        vectorInvitados = new ArrayList<>();
        vectorInvitados.clear();

        editNombreInvitado = (EditText) findViewById(R.id.txtNombreInvitado);
        editCedulaInvitado = (EditText) findViewById(R.id.txtCedulaInvitado);
        editPlacaOpcional = (EditText) findViewById(R.id.txtPlaca);
        editMazana = (EditText) findViewById(R.id.txtManzana);
        editVilla = (EditText) findViewById(R.id.txtVila);
        editEmailInvitado = (EditText) findViewById(R.id.txtVila);


        firebaseAuth = FirebaseAuth.getInstance();
        usuarioLogueado = firebaseAuth.getUid();
        emailUsuarioLogueado = firebaseAuth.getCurrentUser().getEmail();
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
                llenarListaInvitadosConJson();
                //listViewInvitados = (ListView)findViewById(R.id.lvInvitados);
                //adaptador_item_invitados = new Adaptador_Item_Invitados(Lista_Invitados.this, vectorInvitados);
                //listViewInvitados.setAdapter(adaptador_item_invitados);


            }

        });


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


    public boolean registroCompleto(){
        boolean estado = true;
        if(vectorInvitados.size() == 0){

            estado = false;
        }else {

        }
        return estado;
    }

    public void llenarListaInvitadosConJson(){
        vectorInvitados.clear();
        int pos = emailUsuarioLogueado.indexOf("@");
        String enamilLog = "";
        enamilLog= emailUsuarioLogueado.substring(0, pos);
        invitados.child("Usuario:"+enamilLog).child("Invitados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Vector<String> jsonInvitados = new Vector<>();

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
                        invitadoTmp.setInvitadoId(jsonInvitados.get(4));
                        invitadoTmp.setNombreInvitado(jsonInvitados.get(6));
                        invitadoTmp.setCedulaInvitado(jsonInvitados.get(0));
                        invitadoTmp.setPlaca(jsonInvitados.get(7));
                        invitadoTmp.setManzana(jsonInvitados.get(5));
                        invitadoTmp.setVilla(jsonInvitados.get(8));
                        invitadoTmp.setIdUsuario(jsonInvitados.get(3));
                        invitadoTmp.setEstado(Boolean.valueOf(jsonInvitados.get(2)));
                        invitadoTmp.setEnamilUsuario(jsonInvitados.get(1));

                        if(!invitadoTmp.isEstado()){
                            vectorInvitados.add(invitadoTmp);
                        }else {
                            Log.w("porfalsoDesdeResidente", "invitado ya ingreso");
                        }

                        jsonInvitados.clear();
                        contador=0;
                    }
                    Log.w("MesajeDebug", String.valueOf(vectorInvitados.size()));
                    Intent intentItem = new Intent(Lista_Invitados.this, Lista_Invitados_Generada.class);
                    intentItem.putParcelableArrayListExtra("key", vectorInvitados);
                    startActivityForResult(intentItem, 0);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Invitado> getVectorInvitados() {
        return vectorInvitados;
    }

    public void setVectorInvitados(ArrayList<Invitado> vectorInvitados) {
        this.vectorInvitados = vectorInvitados;
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
            Invitado tmpInvitado = new Invitado(tmpIdInvitado,tmpNombreInvitado, tmpCedulaInvitado,tmpPlaca,tmpManzana,tmpVilla, usuarioLogueado, false, emailUsuarioLogueado);
            invitados.child("ListaInvitados"+usuarioLogueado).child("Invitados").child(tmpIdInvitado).setValue(tmpInvitado);
            Toast.makeText(this,"Usuario Agregado con exito", Toast.LENGTH_LONG).show();
            editNombreInvitado.setText("");
            editCedulaInvitado.setText("");
            editPlacaOpcional.setText("");
            editMazana.setText("");
            editVilla.setText("");
        }else {
            Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }



}
