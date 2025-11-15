package pro.AministrarClientes.AgregarCliente;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pro.MenuPrincipal.Main;
import pro.Utilidades.Busqueda;
import pro.Utilidades.ConexionSQL;

public class ControladorAgregarCliente {
    InterfazClientesAgregar interfaz;
    Main control;
    public ControladorAgregarCliente(InterfazClientesAgregar interfaz,Main control){
        this.interfaz= interfaz;
        this.control=control;
        interfaz.getAtras().setOnAction(evento->volverAtras());
        interfaz.getConfirmar().setOnAction(evento->confirmar());
    }


    //Inserta el cliente en la base de datos segun los valores insertados en la interfaz
    private void confirmar(){
        String nombre=interfaz.getNombre().getText().toUpperCase();
        String apellido=interfaz.getApellido().getText().toUpperCase();
        String telefono= interfaz.getTelefono().getText().toUpperCase();
        String localidad= interfaz.getLocalidad().getText().toUpperCase();
        Alert alerta;
        if(Busqueda.busquedaCliente(nombre,apellido)==-1){
            ConexionSQL.operacionIUD(String.format("INSERT INTO CLIENTE(nombre,apellido,localidad,telefono) VALUES (\"%s\",\"%s\",\"%s\",\"%s\")",nombre,apellido,localidad,telefono));
            alerta= new Alert(AlertType.INFORMATION,"Se cargo correctamente el nuevo cliente");
            alerta.setTitle("Confirmacion inserccion");
            alerta.showAndWait();
        }else{
            alerta = new Alert(AlertType.ERROR,"El cliente especificado ya se encuentra en la base de datos");
            alerta.setTitle("Error de inserccion");
            alerta.showAndWait();
        }
    }

    private void volverAtras(){
        control.mostrarMenuPrincipal();
    }
    
}
