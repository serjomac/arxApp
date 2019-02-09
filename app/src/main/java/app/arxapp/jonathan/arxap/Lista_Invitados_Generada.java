package app.arxapp.jonathan.arxap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista_Invitados_Generada extends AppCompatActivity{

    ArrayList<Invitado> arrayInvitados = new ArrayList<>();
    ListView listViewInvitados;
    Adaptador adaptador;
    Lista_Invitados lista_invitados;

    public Lista_Invitados_Generada(Lista_Invitados lista_invitados) {
        this.lista_invitados = lista_invitados;
    }

    public Lista_Invitados_Generada() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_invitados_generada);

        listViewInvitados = findViewById(R.id.lvInvitados);

        try {
            Bundle extras = getIntent().getExtras();
            arrayInvitados = extras.getParcelableArrayList("key");



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

            adaptador = new Adaptador(this, arrayInvitados);

            listViewInvitados.setAdapter(adaptador);



            //Log.w("listaGeneradaLLave", String.valueOf("=======>>>"+ listViewInvitados.toString()));


        }catch (Exception e){
            Log.w("listaGeneradaLLave", e);
        }



    }
}
