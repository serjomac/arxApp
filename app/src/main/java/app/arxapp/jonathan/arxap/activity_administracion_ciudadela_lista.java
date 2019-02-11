package app.arxapp.jonathan.arxap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_administracion_ciudadela_lista extends AppCompatActivity {

    ArrayList<Usuario> vectorInvitados = new ArrayList<>();
    ArrayList<String > estadosTemprales = new ArrayList<>();
    String usurioLogueado;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference invitados;
    String emailUsuarioActual;
    ArrayList<String> jsonInvitados = new ArrayList<>();
    ArrayAdapter<String> usuariosListaEmail;
    ListView listViewInvitados;
    AdaptadorUsuario adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_ciudadela_lista);
        listViewInvitados = (ListView) findViewById(R.id.lvUsuariosLista);
        invitados = FirebaseDatabase.getInstance().getReference("arxappDataBase");

        firebaseAuth = FirebaseAuth.getInstance();
         adaptador = new AdaptadorUsuario(activity_administracion_ciudadela_lista.this,
                vectorInvitados);
        //FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //usurioLogueado = firebaseUser.getUid();
        //emailUsuarioActual = firebaseUser.getEmail();

        llenarListaInvitadosConJson();
        //Toast.makeText(this, "Ya mi tio", Toast.LENGTH_LONG).show();
        listViewInvitados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int position, long id) {

                String correo= vectorInvitados.get(position).getCorreo();
                Intent i= new Intent(activity_administracion_ciudadela_lista.this, Lista_Invitados_Generada.class);
                i.putExtra(Lista_Invitados_Generada.correo ,correo );
                startActivity(i);
                //Toast.makeText(activity_administracion_ciudadela_lista.this, correo, Toast.LENGTH_SHORT).show();


            }
        });

    }


    public void llenarListaInvitadosConJson(){



        invitados.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vectorInvitados.clear();
                adaptador.notifyDataSetChanged();

                int contador =0;
                //Lista_Invitados invitadoTmp = dataSnapshot.getValue(Lista_Invitados.class);
                if(dataSnapshot.getValue() != null){


                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Usuario usuarioTpm = new Usuario();
                        //jsonInvitados.add(child.getValue().toString());
                        usuarioTpm.setCorreo(child.getKey());
                        //Toast.makeText(activity_administracion_ciudadela_lista.this, usuarioTpm.getCorreo(), Toast.LENGTH_SHORT).show();
                        long contadorDeInvitados = child.child("Invitados").getChildrenCount();
                        //Toast.makeText(activity_administracion_ciudadela_lista.this, String.valueOf(contadorDeInvitados),
                         //       Toast.LENGTH_LONG).show();


                        for (DataSnapshot child2 : child.getChildren()) {

                            for (DataSnapshot child3 : child2.getChildren()) {


                                String estadoTempral = String.valueOf(child3.child("estado").getValue());

                                if (estadoTempral.equals("true")) {
                                    estadosTemprales.add(estadoTempral);
                                }

                            }

                        }

                        if (estadosTemprales.size() < contadorDeInvitados) {
                            vectorInvitados.add(usuarioTpm);
                        }else {

                        }
                        estadosTemprales.clear();
                    }




                    Log.w("ListaDeGarita", String.valueOf(vectorInvitados.size()));



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
        for (Usuario invitado:vectorInvitados){
            //usuariosListaEmail.add("Lista de invitados del usuario: " + invitado.getEnamilUsuario());
            usuariosListaEmail = new ArrayAdapter<String>(this,R.layout.item_invitado);

        }


    }
}
