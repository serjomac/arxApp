package app.arxapp.jonathan.arxap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Adaptador extends BaseAdapter {
    Activity activity;
    ArrayList<Invitado> listaDeInvitados;


    public Adaptador(Activity activity, ArrayList<Invitado> listaDeInvitados) {
        this.activity = activity;
        this.listaDeInvitados = listaDeInvitados;
    }

    @Override
    public int getCount() {
        return listaDeInvitados.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeInvitados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_invitado, null);
        }


        //Log.w("debugmuestainvitado", String.valueOf(listaDeInvitados.size()));
        //Invitado item = (Invitado) getItem(position);
        //final View vista = layoutInflater.inflate(R.layout.item_invitado, null);
        //convertView = LayoutInflater.from(contexto).inflate(R.layout.item_invitado, null);


        TextView viewNombre =  v.findViewById(R.id.txtNombreInvitadoItem);
        TextView viewCedulaInvitado =  v.findViewById(R.id.txtCedulaInvitadoItem);
        TextView viewPlaca =  v.findViewById(R.id.txtPlacaItem);
        TextView viewManzana =  v.findViewById(R.id.txtManzanaItem);
        TextView viewVilla =  v.findViewById(R.id.txtVillaItem);

        TextView viewEstado =  v.findViewById(R.id.txtEstadoItem);


        Invitado invitadoTemporal = listaDeInvitados.get(position);


        viewNombre.setText(invitadoTemporal.getNombreInvitado());
        viewCedulaInvitado.setText(invitadoTemporal.getCedulaInvitado());
        viewPlaca.setText(invitadoTemporal.getPlaca());
        viewManzana.setText(invitadoTemporal.getManzana());
        viewVilla.setText(invitadoTemporal.getVilla());

        viewEstado.setText(String.valueOf(invitadoTemporal.isEstado()));

        return v;
    }
}
