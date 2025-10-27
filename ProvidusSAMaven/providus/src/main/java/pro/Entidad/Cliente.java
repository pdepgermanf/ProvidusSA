package pro.Entidad;


public class Cliente {
    String nombre;
    String apellido;
    String localidad;
   
    public Cliente(String nombre, String apellido, String localidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
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

    
}
