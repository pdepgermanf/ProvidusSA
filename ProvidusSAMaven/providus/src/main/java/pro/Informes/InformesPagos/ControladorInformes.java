package pro.Informes.InformesPagos;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pro.Main;
import pro.Entidad.InformePagos;
import pro.Informes.Fila;
import pro.Utilidades.Busqueda;
import pro.Utilidades.ConexionSQL;

public class ControladorInformes {

    InterfazInformePagos ventanaInformes;
    InformePagos informe;
    Main control;

    public ControladorInformes(InterfazInformePagos ventanaInformes, Main control) {
        this.control = control;
        this.ventanaInformes = ventanaInformes;
        this.informe= new InformePagos(ventanaInformes.getTitulo().getText());
        agregarManejadores();

    }

    public void agregarManejadores() {
        ventanaInformes.getAtras().setOnAction(evento -> volverAtras(evento));
        ventanaInformes.getConfirmar().setOnAction(evento -> {
            try {
                confirmar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        ventanaInformes.getBuscar().setOnAction(evento->{
            try {
                buscarPlan();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ventanaInformes.getConfAgregarPlan().setOnAction(evento->{
            try {
                agregarPlan();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        ventanaInformes.getGuardar().setOnAction(evento->guardar());

    }

    //Busca el plan introducido
    public void buscarPlan() throws SQLException{
        int numeroPlan = Integer.parseInt(ventanaInformes.getPlan().getText());
        //Verifica si existe dicho plan
        if(Busqueda.existePlan(numeroPlan)){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmacion");
            alerta.setContentText("El plan se encuentra cargado");
            alerta.showAndWait();

        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("El plan no existe en la base de datos, por favor insertelo");
            alerta.showAndWait();
            ventanaInformes.getBarraAgregarPlan().setVisible(true);
            ventanaInformes.getBarraAgregarPlan().setManaged(true);
        }


    }

    public void agregarPlan() throws SQLException{
        String nroPlan= ventanaInformes.getNumeroDePlan().getText();
        String importePlan= ventanaInformes.getImporteDelPlan().getText();
        int idCliente= Busqueda.busquedaCliente(ventanaInformes.getNombreCliente().getText(), ventanaInformes.getApellidoCliente().getText());
        String sql=String.format("insert into PLAN(id_plan,importe_plan,id_cliente) values (\"%s\",\"%s\",%d);",nroPlan,importePlan,idCliente);
        //Comprueba que se haya realizado la inserccion
        if(ConexionSQL.operacionIUD(sql)!=0){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Carga correctamente");
            alerta.setContentText("Se cargo el plan correctamente");
            alerta.showAndWait();
            ventanaInformes.ocultarBarraAgregarPlan();
        }else{
            System.out.println("No se pudo procesar la inserccion del plan");
        }
    }

    // Busca la informacion en la BD y actualiza la tabla.
    public void confirmar() throws SQLException {
        ResultSet resultado;
        int numeroPlan = Integer.parseInt(ventanaInformes.getPlan().getText());

        String consultaSql = "select nombre, apellido,localidad,id_plan, importe_plan from cliente,plan where cliente.id_cliente=plan.id_cliente and plan.id_plan="
                + numeroPlan;
        resultado = ConexionSQL.consultar(consultaSql);
        // Se posiciona en la primera fila
        if(resultado.next()){
            // Actializa la tabla
            Fila fila = new Fila(resultado.getString(1), resultado.getString(2), resultado.getString(3),numeroPlan ,
                        Integer.parseInt(ventanaInformes.getNumeroCuota().getText()), resultado.getDouble(4), ventanaInformes.getTipoDePago().getValue(), ventanaInformes.getBanco().getValue(),
                        ventanaInformes.getFecha().getValue().toString(), Double.parseDouble(ventanaInformes.getImporte().getText()), ventanaInformes.getNumeroTransaccion().getText());

            ventanaInformes.agregarFila(fila);

            //Agrega la entrada al informe
            informe.agregarEntrada(fila);
            
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING, "El numero de plan no existe");
            alerta.showAndWait();
        }
        

    }

    public void guardar(){
        ArrayList<Fila> lista = informe.getEntradas();
        String sql;
        int resultado;
        sql= String.format("INSERT INTO INFORME(tipo,titulo,fecha) values (\"%s\",\"%s\",\"%s\");","Pago",informe.getTitulo(),informe.getFecha());
        resultado=ConexionSQL.operacionIUD(sql);
        if (resultado==0) {
            System.out.println("Fallo al insertar el informe en la base de datos");
        }
        resultado=0;


        for (Fila fila : lista) {
            sql= String.format("INSERT INTO PAGO(numero_transaccion,tipo_pago,nom_banco,importe_pago,fecha_transaccion) values(\"%s\",\"%s\",\"%s\",%f,\"%s\") ",fila.getPago().getNumeroTransaccion(),fila.getPago().getTipoPago(), fila.getPago().getBanco(),fila.getPago().getImporte(), fila.getPago().getFecha());
            resultado=ConexionSQL.operacionIUD(sql);
            if (resultado==0) {
                System.out.println("Fallo al insertar el pago en la base de datos");
            }
            resultado=0;
            sql= String.format("INSERT INTO CUOTA(id_plan,num_cuota,importe_cuota,estado,numero_transaccion) values(%d,%d,%f,\"%s\",\"%s\")",fila.getPlan().getNumeroPlan(),fila.getPlan().getNumeroCuota(),fila.getPlan().getImporte(),"pagado",fila.getPago().getNumeroTransaccion());
            resultado=ConexionSQL.operacionIUD(sql);
            if (resultado==0) {
                System.out.println("Fallo al insertar la cuota en la base de datos");
            }
            sql= String.format("insert into DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion) values(%d,%d,%d,\"%s\")",informe.getIdInforme(),fila.getPlan().getNumeroPlan(),fila.getPlan().getNumeroCuota(),"Pago de cuota");

            resultado=ConexionSQL.operacionIUD(sql);
            if (resultado==0) {
                System.out.println("Fallo al insertar el detalle de informe");
            }

            
        }
    }

    public void volverAtras(ActionEvent e) {
        control.mostrarMenuPrincipal();
    }

    public void guardarEnBaseDeDatos() {

    }

}
