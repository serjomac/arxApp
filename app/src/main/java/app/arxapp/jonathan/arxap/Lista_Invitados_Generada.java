package app.arxapp.jonathan.arxap;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Vector;

public class Lista_Invitados_Generada extends AppCompatActivity{

    ArrayList<Invitado> arrayInvitados = new ArrayList<>();
    ListView listViewInvitados;
    Adaptador adaptador;
    Lista_Invitados lista_invitados;
    private DatabaseReference referencia;
    String enamil = null;
    ArrayList<Invitado> vectorInvitados = new ArrayList<>();
    public static final String correo = "emailUsuarioSeleccionado";
    public Lista_Invitados_Generada(Lista_Invitados lista_invitados) {
        this.lista_invitados = lista_invitados;
    }

    public Lista_Invitados_Generada() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_invitados_generada);
        enamil = getIntent().getStringExtra("emailUsuarioSeleccionado");
        listViewInvitados = findViewById(R.id.lvInvitados);
        referencia = FirebaseDatabase.getInstance().getReference("arxappDataBase");

        try {
            Bundle extras = getIntent().getExtras();
            arrayInvitados = extras.getParcelableArrayList("key");
            if(arrayInvitados==null){
                llenarDatosDelUsuarioDesdeAdmin();
                adaptador = new Adaptador(this, vectorInvitados);
                darEventoAdmin();
                //Toast.makeText(Lista_Invitados_Generada.this,enamil,Toast.LENGTH_LONG).show();
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adaptador = new Adaptador(Lista_Invitados_Generada.this, arrayInvitados);
                    }
                });


            }




            //adaptador_item_invitados = new Adaptador_Item_Invitados(this, arrayInvitados);

            /*
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getInvitadoId());
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getNombreInvitado());
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getCedulaInvitado());
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getPlaca());
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getManzana());
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getVilla());
                Log.w("listaGeneradaLLave", "------!!!!"+ arrayInvitados.get(1).getIdUsuario());
                Log.w("listaGeneradaLLave", String.valueOf("------!!!!"+ arrayInvitados.get(1).isEstado()));
            */



            listViewInvitados.setAdapter(adaptador);



            //Log.w("listaGeneradaLLave", String.valueOf("=======>>>"+ listViewInvitados.toString()));


        }catch (Exception e){
            Log.w("listaGeneradaLLave", e);
        }



    }
    public void darEventoAdmin(){
        listViewInvitados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int position, long id) {

                String invitado= vectorInvitados.get(position).getInvitadoId();
                dialog(invitado);

            }
        });
    }
    private  void dialog(final  String invitado){
        final Dialog dialog= new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogo_message);
        Button btnAcep= dialog.findViewById(R.id.btnFavor);
        Button btnCan= dialog.findViewById(R.id.btnCancelar);

        btnAcep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                enviarMensaje(invitado);

                try {
                    referencia.child(enamil).child("Invitados").child(invitado).child("estado").setValue("true");
                    Vibrator vibrar = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibrar.vibrate(1000);

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            adaptador.notifyDataSetChanged();
                        }
                    };

                    runOnUiThread(r);



                } catch (Exception e){
                    Log.w("estadoFallido", e);
                }



            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public void enviarMensaje(String invitado){
        //Toast.makeText(Lista_Invitados_Generada.this, invitado, Toast.LENGTH_SHORT).show();

    }
    public void llenarDatosDelUsuarioDesdeAdmin(){


        referencia.child(enamil).child("Invitados").addValueEventListener(new ValueEventListener() {
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
                            Log.w("porfalso", "El estado es true entonces no lo agrega");
                        }

                        jsonInvitados.clear();
                        contador=0;
                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
