package pro.Entidad;


public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String localidad;
    private String telefono;
   
    public Cliente(String nombre, String apellido, String localidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
    }

    public Cliente(int id, String nombre, String apellido, String localidad, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
        this.telefono = telefono;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
