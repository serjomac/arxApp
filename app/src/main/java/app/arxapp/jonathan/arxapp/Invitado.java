package app.arxapp.jonathan.arxapp;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Invitado {



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
    private boolean estado;


    public Invitado(String invitadoId, String nombreInvitado, String cedulaInvitado, String placa,
                    String manzana, String villa, String idUsuario, boolean estado) {
        this.invitadoId = invitadoId;
        this.nombreInvitado = nombreInvitado;
        this.cedulaInvitado = cedulaInvitado;
        this.placa = placa;
        this.manzana = manzana;
        this.villa = villa;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    public Invitado() {

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





}
