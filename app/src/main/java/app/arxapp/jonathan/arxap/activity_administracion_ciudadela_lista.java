package app.arxapp.jonathan.arxap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_administracion_ciudadela_lista extends AppCompatActivity {

    ArrayList<Invitado> vectorInvitados = new ArrayList<>();

    String usurioLogueado;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference invitados;
    String emailUsuarioActual;
    ArrayList<String> jsonInvitados = new ArrayList<>();
    ArrayAdapter<String> usuariosListaEmail;
    ListView listViewInvitados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_ciudadela_lista);
        listViewInvitados = (ListView) findViewById(R.id.lvUsuariosLista);
        invitados = FirebaseDatabase.getInstance().getReference("arxappDataBase");

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //usurioLogueado = firebaseUser.getUid();
        //emailUsuarioActual = firebaseUser.getEmail();

        llenarListaInvitadosConJson();
        //Toast.makeText(this, "Ya mi tio", Toast.LENGTH_LONG).show();

    }


    public void llenarListaInvitadosConJson(){
        vectorInvitados.clear();


        invitados.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                int contador =0;
                //Lista_Invitados invitadoTmp = dataSnapshot.getValue(Lista_Invitados.class);
                if(dataSnapshot.getValue() != null){
                    //Toast.makeText(Lista_Invitados.this, "d" , Toast.LENGTH_SHORT).show();

                    for (DataSnapshot listaInvitadohild : dataSnapshot.getChildren()) {
                        for (DataSnapshot invitadosChild : listaInvitadohild.getChildren()) {
                            for (DataSnapshot child : invitadosChild.getChildren()) {
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
                                vectorInvitados.add(invitadoTmp);
                                Log.w("cantidadDeInvitados" , "Cantidad de invitados"
                                        + String.valueOf(vectorInvitados.size()) );
                                jsonInvitados.clear();
                                contador=0;
                            }
                        }
                    }


                    Log.w("ListaDeGarita", String.valueOf(vectorInvitados.size()));
                    Adaptador adaptador = new Adaptador(activity_administracion_ciudadela_lista.this,
                            vectorInvitados);

                    listViewInvitados.setAdapter(adaptador);
                    //Intent intentItem = new Intent(activity_administracion_ciudadela_lista.this,
                            //Lista_Invitados_Generada.class);
                    //intentItem.putParcelableArrayListExtra("key", vectorInvitados);
                    //startActivityForResult(intentItem, 0);

                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void cargarListView(){
            for (Invitado invitado:vectorInvitados){
                //usuariosListaEmail.add("Lista de invitados del usuario: " + invitado.getEnamilUsuario());
                usuariosListaEmail = new ArrayAdapter<String>(this,R.layout.item_invitado);

            }


        }
}
