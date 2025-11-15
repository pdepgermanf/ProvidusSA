package pro.AministrarClientes.ConsultarCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import pro.Entidad.Cliente;
import pro.MenuPrincipal.Main;
import pro.Utilidades.ConexionSQL;

public class ControladorConsultarCliente {
    private InterfazConsultarCliente interfaz;
    private Main control;

    public ControladorConsultarCliente(InterfazConsultarCliente interfaz, Main control) {
        this.interfaz = interfaz;
        this.control = control;
        actualizarTabla();
        interfaz.getAtras().setOnAction(evento -> volverAtras());
        interfaz.getBotonBuscar().setOnAction(evento->filtrarClientes());
    }


    //Recupera los cliente existentes en la base de datos
    private void actualizarTabla() {
        String consulta = "SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE";
        ResultSet resultado = ConexionSQL.consultar(consulta);
        String nombre;
        String apellido;
        String telefono;
        String localidad;
        int id;
        try {
            while (resultado.next()) {
                id = resultado.getInt(1);
                nombre = resultado.getString(2);
                apellido = resultado.getString(3);
                localidad = resultado.getString(4);
                telefono = resultado.getString(5);
                interfaz.añadirFila(new Cliente(id,nombre, apellido, localidad,telefono));
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la tabla");
        }
    }


    //Filtra los cliente con ciertos atributos
    private void filtrarClientes(){
        String consulta="";
        String busqueda= interfaz.getBuscador().getText();
        //Determina en base a que parametro busca
        if(interfaz.getOpcionID().isSelected()){
            //En el caso que se ingrese una cadena
            try {
                consulta=String.format("SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE WHERE id_cliente= %d",Integer.parseInt(busqueda));
            } catch (NumberFormatException e) {
                System.out.println("No es un numero");
            }
            
        }else if (interfaz.getOpcionNombre().isSelected()) {
            consulta=String.format("SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE WHERE nombre= \"%s\"",busqueda);
        }else if(interfaz.getOpcionApellido().isSelected()) {
            consulta=String.format("SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE WHERE apellido= \"%s\"",busqueda);
        }else{
            consulta=String.format("SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE WHERE localidad= \"%s\"",busqueda);
        }
        ResultSet resultado = ConexionSQL.consultar(consulta);
        interfaz.getTablaClientes().getItems().clear();
        String nombre;
        String apellido;
        String telefono;
        String localidad;
        int id;
        try {
            while (resultado.next()) {
                id = resultado.getInt(1);
                nombre = resultado.getString(2);
                apellido = resultado.getString(3);
                localidad = resultado.getString(4);
                telefono = resultado.getString(5);
                interfaz.añadirFila(new Cliente(id,nombre, apellido, localidad,telefono));
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la tabla");
        }
    }

    private void volverAtras() {
        control.mostrarMenuPrincipal();
    }

}
