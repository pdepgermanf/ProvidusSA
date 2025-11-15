package pro.AministrarClientes.ModificarCliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pro.MenuPrincipal.Main;
import pro.Utilidades.ConexionSQL;

public class ControladorModificarCliente {
    private InterfazModificarCliente interfaz;
    private Main control;

    public ControladorModificarCliente(InterfazModificarCliente interfaz, Main control) {
        this.interfaz = interfaz;
        this.control = control;
        interfaz.getAtras().setOnAction(evento -> volverAtras());
        interfaz.getOpcionID().setOnAction(evento -> mostrarOpciones());
        interfaz.getOpcionNombreApellido().setOnAction(evento -> mostrarOpciones());
        interfaz.getBotonBuscar().setOnAction(evento -> mostrarDatosCliente());
        interfaz.getGuardarCambios().setOnAction(evento -> guradarDatosModificados());

    }

    // Muestra los campos solicitados para la busqueda por id o por nombre y
    // apellido
    private void mostrarOpciones() {
        System.out.println("llego");
        if (interfaz.getOpcionNombreApellido().isSelected()) {
            interfaz.getBuscadorNombre().setVisible(true);
            interfaz.getBuscadorApellido().setVisible(true);
            interfaz.getBuscadorNombre().setManaged(true);
            interfaz.getBuscadorApellido().setManaged(true);

            interfaz.getBuscadorID().setVisible(false);
            interfaz.getBuscadorID().setManaged(false);

        } else {
            interfaz.getBuscadorNombre().setVisible(false);
            interfaz.getBuscadorApellido().setVisible(false);
            interfaz.getBuscadorNombre().setManaged(false);
            interfaz.getBuscadorApellido().setManaged(false);

            interfaz.getBuscadorID().setVisible(true);
            interfaz.getBuscadorID().setManaged(true);
        }
    }

    private void mostrarDatosCliente() {
        String sql;
        ResultSet resultado;
        int id = -1;
        String nombre = "";
        String apellido = "";

        if (interfaz.getOpcionNombreApellido().isSelected()) {
            nombre = interfaz.getBuscadorNombre().getText().toUpperCase();
            apellido = interfaz.getBuscadorApellido().getText().toUpperCase();
            sql = String.format(
                    "SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE WHERE nombre=\"%s\" && apellido=\"%s\"",
                    nombre, apellido);
            resultado = ConexionSQL.consultar(sql);
        } else {
            id = Integer.parseInt(interfaz.getBuscadorID().getText());
            sql = String.format("SELECT id_cliente,nombre,apellido,localidad,telefono FROM CLIENTE WHERE id_cliente=%d",
                    id);
            resultado = ConexionSQL.consultar(sql);
        }
        try {
            if (resultado.next()) {
                interfaz.getId().setText(String.valueOf(resultado.getInt(1)));
                interfaz.getNombre().setText(resultado.getString(2));
                interfaz.getApellido().setText(resultado.getString(3));
                interfaz.getLocalidad().setText(resultado.getString(4));
                interfaz.getTelefono().setText(resultado.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Actualiza segun los datos introducidos
    public void guradarDatosModificados() {

        if (interfaz.getId().getText() != "") {
            int id = Integer.parseInt(interfaz.getId().getText());
            String nombre = interfaz.getNombre().getText().toUpperCase();
            String apellido = interfaz.getApellido().getText().toUpperCase();
            String localidad = interfaz.getLocalidad().getText();
            String telefono = interfaz.getTelefono().getText();

            String sql = String.format("UPDATE CLIENTE SET nombre=\"%s\",apellido=\"%s\",localidad=\"%s\", telefono=\"%s\" WHERE id_cliente=%d",nombre, apellido, localidad, telefono, id);
            Alert alerta;
            if (ConexionSQL.operacionIUD(sql) != 0) {
                alerta = new Alert(AlertType.CONFIRMATION, "Se modifico correctamente los datos del cliente");

            } else {
                alerta = new Alert(AlertType.ERROR, "No puedo modificarse los datos del cliente");
            }
            alerta.showAndWait();
        }
        interfaz.limpiarValores();
    }

    private void volverAtras() {
        control.mostrarMenuPrincipal();
    }

}
