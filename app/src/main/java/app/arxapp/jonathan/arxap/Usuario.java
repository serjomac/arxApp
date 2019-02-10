package app.arxapp.jonathan.arxap;

public class Usuario {


    private String nombre;
    private String correo;
    private String idUduario;
    private String manzana;
    private String villa;
    private String clave;
    private String nombreUsuario;


    public Usuario( String idUduario,String nombre, String correo, String manzana, String villa, String clave) {
        this.idUduario = idUduario;
        this.nombre = nombre;
        this.correo = correo;
        this.manzana = manzana;
        this.villa = villa;
        this.clave = clave;
    }
    public  Usuario(){

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdUduario() {
        return idUduario;
    }

    public void setIdUduario(String idUduario) {
        this.idUduario = idUduario;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
