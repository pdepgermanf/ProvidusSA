package pro.Entidad;

public class Cobrador {
    public Cobrador(int id, String nombre, String apellido, String localidad, String telf, String contraseña,
            String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
        this.telf = telf;
        this.contraseña = contraseña;
        this.usuario = usuario;
    }
    int id;
    String nombre;
    String apellido;
    String localidad;
    String telf;
    String contraseña;
    String usuario;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getTelf() {
        return telf;
    }
    public void setTelf(String telf) {
        this.telf = telf;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
}
