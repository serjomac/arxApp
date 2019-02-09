package app.arxapp.jonathan.arxap;


import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Invitado implements Parcelable {

    private String invitadoId;
    private String nombreInvitado;
    private String cedulaInvitado;
    private String placa;

    public Invitado(String manzana) {
        this.manzana = manzana;
    }

    private String manzana;
    private String villa;
    public String idUsuario;
    public String enamilUsuario;

    private boolean estado;


    public Invitado(String nombreInvitado, String cedulaInvitado, String placa, String manzana, String villa,String enamilUsuario) {
        this.nombreInvitado = nombreInvitado;
        this.cedulaInvitado = cedulaInvitado;
        this.placa = placa;
        this.manzana = manzana;
        this.villa = villa;

    }

    public Invitado(String invitadoId, String nombreInvitado, String cedulaInvitado, String placa,
                    String manzana, String villa, String idUsuario, boolean estado, String enamilUsuario) {
        this.invitadoId = invitadoId;
        this.nombreInvitado = nombreInvitado;
        this.cedulaInvitado = cedulaInvitado;
        this.placa = placa;
        this.manzana = manzana;
        this.villa = villa;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.enamilUsuario = enamilUsuario;
    }

    public Invitado() {

    }

    public Invitado(Parcel source) {
        this.invitadoId = source.readString();
        this.nombreInvitado = source.readString();
        this.cedulaInvitado = source.readString();
        this.placa = source.readString();
        this.manzana = source.readString();
        this.villa = source.readString();
        this.idUsuario = source.readString();
        this.estado = Boolean.valueOf(source.readString());

    }


    public String getEnamilUsuario() {
        return enamilUsuario;
    }

    public void setEnamilUsuario(String enamilUsuario) {
        this.enamilUsuario = enamilUsuario;
    }

    public String getInvitadoId() {
        return invitadoId;
    }

    public void setInvitadoId(String invitadoId) {
        this.invitadoId = invitadoId;
    }

    public String getNombreInvitado() {
        return nombreInvitado;
    }

    public void setNombreInvitado(String nombreInvitado) {
        this.nombreInvitado = nombreInvitado;
    }

    public String getCedulaInvitado() {
        return cedulaInvitado;
    }

    public void setCedulaInvitado(String cedulaInvitado) {
        this.cedulaInvitado = cedulaInvitado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getVilla() {
        return villa;
    }

    public void setVilla(String villa) {
        this.villa = villa;
    }



    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }




    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getInvitadoId());
        dest.writeString(getNombreInvitado());
        dest.writeString(getCedulaInvitado());
        dest.writeString(getPlaca());
        dest.writeString(getManzana());
        dest.writeString(getVilla());
        dest.writeString(getIdUsuario());
        dest.writeString(String.valueOf(isEstado()));

    }

    public static final Parcelable.Creator<Invitado> CREATOR = new Parcelable.Creator<Invitado>(){

        @Override
        public Invitado createFromParcel(Parcel source) {
            return new Invitado(source);
        }

        @Override
        public Invitado[] newArray(int size) {
            return new Invitado[size];
        }
    };
}
