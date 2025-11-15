package pro.AministrarClientes.EliminarCliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pro.MenuPrincipal.Main;
import pro.Utilidades.ConexionSQL;

public class ControladorEliminarCliente {
    private InterfazEliminarCliente interfaz;
    private Main control;

    public ControladorEliminarCliente(InterfazEliminarCliente interfaz, Main control) {
        this.interfaz = interfaz;
        this.control = control;
        interfaz.getAtras().setOnAction(evento -> volverAtras());
        interfaz.getOpcionID().setOnAction(evento -> mostrarOpciones());
        interfaz.getOpcionNombreApellido().setOnAction(evento -> mostrarOpciones());
        interfaz.getBotonBuscar().setOnAction(evento -> mostrarDatosCliente());
        interfaz.getBotonEliminar().setOnAction(evento -> eliminar());

    }

    // Muestra los campos solicitados para la busqueda por id o por nombre y
    // apellido
    private void mostrarOpciones() {
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

    //Realiza la consulta en la base de datos de acuerdo a los valores de entrada y actualiza en la interfaz para mostrar datos asociados
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

    // Elimina el cliente y sus planes asociados
    public void eliminar() {

        if (interfaz.getId().getText() != "") {
            ResultSet resultado;
            int borrado=0;
            int id = Integer.parseInt(interfaz.getId().getText());
            String sql = String.format("SELECT id_plan FROM PLAN WHERE id_cliente=%d", id);

            resultado = ConexionSQL.consultar(sql);
            try {
                while (resultado.next()) {
                    sql = String.format("DELETE FROM DETALLE_INFORME WHERE id_plan=%d;", resultado.getInt(1));
                    ConexionSQL.operacionIUD(sql);
                    sql = String.format("DELETE FROM CUOTA WHERE id_plan=%d;", resultado.getInt(1));
                    ConexionSQL.operacionIUD(sql);
                }
                sql = String.format("DELETE FROM PLAN WHERE id_cliente=%d;", id);
                ConexionSQL.operacionIUD(sql);
                sql = String.format("DELETE FROM CLIENTE WHERE id_cliente=%d;", id);
                borrado=ConexionSQL.operacionIUD(sql);
                

            } catch (SQLException e) {
                e.printStackTrace();
            }

            Alert alerta;
            if (borrado != 0) {
                alerta = new Alert(AlertType.CONFIRMATION, "Se elimino correctamente el cliente");
            } else {
                alerta = new Alert(AlertType.ERROR, "No puedo eliminarse el cliente selccionado");
            }
            alerta.showAndWait();
        }
        interfaz.limpiarValores();
    }

    private void volverAtras() {
        control.mostrarMenuPrincipal();
    }

}
