package app.arxapp.jonathan.arxap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorUsuario extends BaseAdapter {
    Activity activity;
    ArrayList<Usuario> listaDeInvitados;


    public AdaptadorUsuario(Activity activity, ArrayList<Usuario> listaDeInvitados) {
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
            v = inf.inflate(R.layout.item_usuario, null);
        }


        //Log.w("debugmuestainvitado", String.valueOf(listaDeInvitados.size()));
        //Invitado item = (Invitado) getItem(position);
        //final View vista = layoutInflater.inflate(R.layout.item_invitado, null);
        //convertView = LayoutInflater.from(contexto).inflate(R.layout.item_invitado, null);


        TextView viewCorreo =  v.findViewById(R.id.txtCorreoUsuarioItem);


        Usuario invitadoTemporal = listaDeInvitados.get(position);


        viewCorreo.setText(invitadoTemporal.getCorreo());


        return v;
    }
}
