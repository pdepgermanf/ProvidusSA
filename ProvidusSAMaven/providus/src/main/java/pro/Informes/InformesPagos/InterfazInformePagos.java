package pro.Informes.InformesPagos;
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

public class InterfazInformePagos extends InterfazInforme {

    private GridPane barraAgregarPlan;
    private Button confAgregarPlan;
    private TextField numeroDePlan;
    private TextField importeDelPlan;
    private TextField nombreCliente;
    private TextField apellidoCliente;
    private TextField importe;
    private DatePicker fecha;
    private ComboBox<String> tipoDePago;
    private ComboBox<String> banco;
    private TextField numeroTransaccion;

    //Genera la ventana y la muestra 
    public InterfazInformePagos(Main control) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        new ControladorInformesPagos(this,control);
        control.getVentana().setTitle("Informe Pagos");
    }

    //En el caso que se realice una modificacion
    public InterfazInformePagos(Main control,Informe informe) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        new ControladorInformesPagos(this,control,informe);
        control.getVentana().setTitle("Informe Pagos");
    }

    public void ocultarBarraAgregarPlan() {
        barraAgregarPlan.setVisible(false);
        barraAgregarPlan.setManaged(false);

    }

    public void mostrarBarraAgregarPlan() {
        barraAgregarPlan.setVisible(false);
        barraAgregarPlan.setManaged(false);

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
        TableColumn<Fila, Double> colImporteCuota = new TableColumn<>("Importe cuota");
        colImporteCuota.setCellValueFactory(valor -> new SimpleDoubleProperty(valor.getValue().getPlan().getImporte()).asObject());
        TableColumn<Fila, String> colTipoPago = new TableColumn<>("Tipo pago");
        colTipoPago.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getTipoPago()));
        TableColumn<Fila, String> colBanco = new TableColumn<>("Banco");
        colBanco.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getBanco()));
        TableColumn<Fila, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getFecha()));
        TableColumn<Fila, Double> colImporteDeposito = new TableColumn<>("Importe deposito");
        colImporteDeposito.setCellValueFactory(valor -> new SimpleDoubleProperty(valor.getValue().getPago().getImporte()).asObject());
        TableColumn<Fila, String> colNroTansaccion = new TableColumn<>("Numero de transaccion");
        colNroTansaccion.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getNumeroTransaccion()));

        // Añade las columnas
        tabla.getColumns().addAll(colNomCliente, colApCliente, colLocalidadCliente, colNumPlan, colNumCuota,
                colImporteCuota, colTipoPago, colBanco, colFecha, colImporteDeposito, colNroTansaccion);
        panelGeneral.setCenter(tabla);
    }

    @Override
    protected void iniciarPanelSuperior() {
        // Lista para almacenar elementos
        Node[] nodos = new Node[9];
        inserccion = new GridPane();
        inserccion.setHgap(5);
        inserccion.setVgap(1);

        // Entradas para panel inserccion
        nodos[0] = plan = new TextField("");
        nodos[1] = buscar = new Button("Buscar");
        nodos[2] = numeroCuota = new TextField();
        nodos[3] = importe = new TextField();
        nodos[4] = fecha = new DatePicker();
        nodos[5] = tipoDePago = new ComboBox<>();
        nodos[6] = banco = new ComboBox<>();
        nodos[7] = numeroTransaccion = new TextField("");
        nodos[8] = confirmar = new Button("Confirmar");

        tipoDePago.getItems().addAll("Efectivo", "Deposito");
        tipoDePago.setValue("Deposito");
        banco.getItems().addAll("Córdoba", "Nación", "MacroBansud", "Francés");

        etiquerasBarraInserccion = new Label[9];
        etiquerasBarraInserccion[0] = new Label("Plan");
        etiquerasBarraInserccion[1] = new Label();
        etiquerasBarraInserccion[2] = new Label("Numero de cuota");
        etiquerasBarraInserccion[3] = new Label("Importe pago");
        etiquerasBarraInserccion[4] = new Label("Fecha");
        etiquerasBarraInserccion[5] = new Label("Tipo de pago");
        etiquerasBarraInserccion[6] = new Label("Banco");
        etiquerasBarraInserccion[7] = new Label("Numero de transaccion");
        etiquerasBarraInserccion[8] = new Label();

        for (int i = 0; i < etiquerasBarraInserccion.length; i++) {
            inserccion.add(etiquerasBarraInserccion[i], i, 0);
            inserccion.add(nodos[i], i, 1);
        }
        barraSuperior.getChildren().add(inserccion);


        Label[] etiquetas = new Label[5];
        nodos = new Node[5];
        barraAgregarPlan = new GridPane();
        barraAgregarPlan.setHgap(5);
        barraAgregarPlan.setVgap(1);

        nodos[0] = numeroDePlan = new TextField();
        nodos[1] = importeDelPlan = new TextField();
        nodos[2] = nombreCliente = new TextField();
        nodos[3] = apellidoCliente = new TextField();
        nodos[4] = confAgregarPlan = new Button("Agregar plan");

        etiquetas[0] = new Label("numero de nuevo plan");
        etiquetas[1] = new Label("Importe del plan");
        etiquetas[2] = new Label("Nombre del cliente");
        etiquetas[3] = new Label("Apellido del cliente");
        etiquetas[4] = new Label();

        for (int i = 0; i < nodos.length; i++) {
            barraAgregarPlan.add(etiquetas[i], i, 0);
            barraAgregarPlan.add(nodos[i], i, 2);
        }
        barraSuperior.getChildren().add(barraAgregarPlan);
        ocultarBarraAgregarPlan();

    }

    public GridPane getBarraAgregarPlan() {
        return barraAgregarPlan;
    }


    public Button getConfAgregarPlan() {
        return confAgregarPlan;
    }


    public TextField getNumeroDePlan() {
        return numeroDePlan;
    }

    public TextField getImporteDelPlan() {
        return importeDelPlan;
    }

    public TextField getNombreCliente() {
        return nombreCliente;
    }

    public TextField getApellidoCliente() {
        return apellidoCliente;
    }

    public TextField getImporte() {
        return importe;
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
