package pro.Informes.InformesActualizacion;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pro.Entidad.Informe;
import pro.Informes.Fila;
import pro.Informes.InterfazInforme;
import pro.MenuPrincipal.Main;

public class InterfazInformeActualizacion extends InterfazInforme{
    private TextField importe;
    private TextField nuevoImporte;
    private DatePicker fecha;
    private ComboBox<String> tipoDePago;
    private ComboBox<String> banco;
    private TextField numeroTransaccion;

    public InterfazInformeActualizacion(Main control) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        new ControladorInformesActualizacion(this,control);
        control.getVentana().setTitle("Informes con actualizacion");
    }

    //En caso de modificacion
    public InterfazInformeActualizacion(Main control,Informe informe) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        new ControladorInformesActualizacion(this,control,informe);
        control.getVentana().setTitle("Informes con actualizacion");
    }

    

    @Override
    protected void iniciarPanelCentral() {
        // Creacion de la tabla
        tabla = new TableView<>();
        tabla.setPadding(new Insets(10, 0, 0, 0));
        // Creacion de las columnas
        // Utilizo un lambda valor para poder acceder a los atributos indirectos
        TableColumn<Fila, String> colNomCliente = new TableColumn<>("Nombre");
        colNomCliente.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getCliente().getNombre()));
        TableColumn<Fila, String> colApCliente = new TableColumn<>("Apellido");
        colApCliente.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getCliente().getApellido()));
        TableColumn<Fila, String> colLocalidadCliente = new TableColumn<>("Localidad");
        colLocalidadCliente.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getCliente().getLocalidad()));
        TableColumn<Fila, Integer> colNumPlan = new TableColumn<>("Numero plan");
        colNumPlan.setCellValueFactory(valor -> new SimpleIntegerProperty(valor.getValue().getPlan().getNumeroPlan()).asObject());
        TableColumn<Fila, Integer> colNumCuota = new TableColumn<>("Numero cuota");
        colNumCuota.setCellValueFactory(valor -> new SimpleIntegerProperty(valor.getValue().getPlan().getNumeroCuota()).asObject());
        TableColumn<Fila, String> colTipoPago = new TableColumn<>("Tipo pago");
        colTipoPago.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getTipoPago()));
        TableColumn<Fila, String> colBanco = new TableColumn<>("Banco");
        colBanco.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getBanco()));
        TableColumn<Fila, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getFecha()));
        TableColumn<Fila, Double> colImporteDeposito = new TableColumn<>("Importe deposito");
        colImporteDeposito.setCellValueFactory(valor -> new SimpleDoubleProperty(valor.getValue().getPago().getImporte()).asObject());
        TableColumn<Fila, Double> colImporteNuevo = new TableColumn<>("Importe nuevo cuota");
        colImporteNuevo.setCellValueFactory(valor -> new SimpleDoubleProperty(valor.getValue().getPlan().getImporte()).asObject());
        TableColumn<Fila, String> colNroTansaccion = new TableColumn<>("Numero de transaccion");
        colNroTansaccion.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getNumeroTransaccion()));

        // Añade las columnas
        tabla.getColumns().addAll(colNomCliente, colApCliente, colLocalidadCliente, colNumPlan, colNumCuota,colImporteNuevo,
        colTipoPago, colBanco, colFecha, colImporteDeposito, colNroTansaccion);
        panelGeneral.setCenter(tabla);
        
    }

    @Override
    protected void iniciarPanelSuperior() {
        // Lista para almacenar elementos
        Node[] nodos = new Node[10];
        inserccion = new GridPane();
        inserccion.setHgap(5);
        inserccion.setVgap(1);

        // Entradas para panel inserccion
        nodos[0] = plan = new TextField("");
        nodos[1] = buscar = new Button("Buscar");
        nodos[2]= numeroCuota = new TextField();
        nodos[3]= nuevoImporte = new TextField();
        nodos[4] = importe = new TextField();
        nodos[5] = fecha = new DatePicker();
        nodos[6] = tipoDePago = new ComboBox<>();
        nodos[7] = banco = new ComboBox<>();
        nodos[8] = numeroTransaccion = new TextField("");
        nodos[9] = confirmar = new Button("Confirmar");

        tipoDePago.getItems().addAll("Efectivo", "Deposito");
        tipoDePago.setValue("Deposito");
        banco.getItems().addAll("Córdoba", "Nación", "MacroBansud", "Francés");

        etiquerasBarraInserccion = new Label[10];
        etiquerasBarraInserccion[0] = new Label("Plan");
        etiquerasBarraInserccion[1] = new Label();
        etiquerasBarraInserccion[2] = new Label("Numero cuota");
        etiquerasBarraInserccion[3] = new Label("Nuevo importe de cuota");
        etiquerasBarraInserccion[4] = new Label("Importe pago");
        etiquerasBarraInserccion[5] = new Label("Fecha");
        etiquerasBarraInserccion[6] = new Label("Tipo de pago");
        etiquerasBarraInserccion[7] = new Label("Banco");
        etiquerasBarraInserccion[8] = new Label("Numero de transaccion");
        etiquerasBarraInserccion[9] = new Label();

        for (int i = 0; i < etiquerasBarraInserccion.length; i++) {
            inserccion.add(etiquerasBarraInserccion[i], i, 0);
            inserccion.add(nodos[i], i, 1);
        }
        barraSuperior.getChildren().add(inserccion);
    }


    public TextField getImporte() {
        return importe;
    }


    public TextField getNuevoImporte() {
        return nuevoImporte;
    }


    public DatePicker getFecha() {
        return fecha;
    }


    public ComboBox<String> getTipoDePago() {
        return tipoDePago;
    }


    public ComboBox<String> getBanco() {
        return banco;
    }


    public TextField getNumeroTransaccion() {
        return numeroTransaccion;
    }

    
    

}