package pro.Informes.InformesPagos;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pro.Main;
import pro.Informes.Fila;
import pro.Utilidades.Busqueda;

public class InterfazInformePagos {
    private Label[] etiquerasBarraInserccion;
    private TextField plan;
    private Button buscar;

    private TextField numeroCuota;
    private TextField importe;
    private DatePicker fecha;
    private ComboBox<String> tipoDePago;
    private ComboBox<String> banco;
    private TextField numeroTransaccion;
    private Button confirmar;
    private TextField titulo;
    private Button guardar;
    private Button atras;
    private GridPane inserccion;
    private VBox barraSuperior;
    private HBox barraUtilidades;
    private BorderPane panelGeneral;
    private TableView<Fila> tabla;
    private ControladorInformes controlador;

    // Para la clase interfaz pago
    private GridPane barraAgregarPlan;
    private Button confAgregarPlan;
    private TextField numeroDePlan;
    private TextField importeDelPlan;
    private TextField nombreCliente;
    private TextField apellidoCliente;

    public InterfazInformePagos(Main control) {
        // Inicia los paneles principales
        barraSuperior = new VBox(5);
        barraSuperior.setPadding(new Insets(0, 10, 0, 10));
        panelGeneral = new BorderPane();
        panelGeneral.setTop(barraSuperior);

        // Inicia las subsecciones
        iniciarTabla();
        iniciarBarraUtilidades();
        iniciarBarraInserccion();
        iniciarBarraAgregarPlan();

        // declara el controlador
        controlador = new ControladorInformes(this, control);
        Scene escena = new Scene(panelGeneral, 1000, 1000);
        control.getVentana().setTitle("Informe");
        control.getVentana().setScene(escena);
    }

    public void iniciarBarraUtilidades() {
        barraUtilidades = new HBox(5);
        // Elmentos internos panel de utilidades
        titulo = new TextField("Titulo");
        guardar = new Button("Guradar informe");
        atras = new Button("Atras");

        // Agrega los nodos de la barra superior
        barraUtilidades.getChildren().addAll(atras, guardar, titulo);
        barraUtilidades.setHgrow(titulo, Priority.ALWAYS);
        barraSuperior.getChildren().add(barraUtilidades);
    }

    public void iniciarBarraInserccion() {
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

    }

    
    public void iniciarTabla() {
        // Creacion de la tabla
        tabla = new TableView<>();
        tabla.setPadding(new Insets(10, 0, 0, 0));
        // Creacion de las columnas
        // Utilizo un lambda valor para poder acceder a los atributos indirectos
        TableColumn<Fila, String> colNomCliente = new TableColumn<>("Nombre");
        colNomCliente.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getCliente().getNombre()));
        TableColumn<Fila, String> colApCliente = new TableColumn<>("Apellido");
        colApCliente
                .setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getCliente().getApellido()));
        TableColumn<Fila, String> colLocalidadCliente = new TableColumn<>("Localidad");
        colLocalidadCliente
                .setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getCliente().getLocalidad()));
        TableColumn<Fila, Integer> colNumPlan = new TableColumn<>("Numero plan");
        colNumPlan.setCellValueFactory(
                valor -> new SimpleIntegerProperty(valor.getValue().getPlan().getNumeroPlan()).asObject());
        TableColumn<Fila, Integer> colNumCuota = new TableColumn<>("Numero cuota");
        colNumCuota.setCellValueFactory(
                valor -> new SimpleIntegerProperty(valor.getValue().getPlan().getNumeroCuota()).asObject());
        TableColumn<Fila, Double> colImporteCuota = new TableColumn<>("Importe cuota");
        colImporteCuota.setCellValueFactory(
                valor -> new SimpleDoubleProperty(valor.getValue().getPlan().getImporte()).asObject());
        TableColumn<Fila, String> colTipoPago = new TableColumn<>("Tipo pago");
        colTipoPago.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getTipoPago()));
        TableColumn<Fila, String> colBanco = new TableColumn<>("Banco");
        colBanco.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getBanco()));
        TableColumn<Fila, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(valor -> new SimpleStringProperty(valor.getValue().getPago().getFecha()));
        TableColumn<Fila, Double> colImporteDeposito = new TableColumn<>("Importe deposito");
        colImporteDeposito.setCellValueFactory(
                valor -> new SimpleDoubleProperty(valor.getValue().getPago().getImporte()).asObject());
        TableColumn<Fila, String> colNroTansaccion = new TableColumn<>("Numero de transaccion");
        colNroTansaccion.setCellValueFactory(
                valor -> new SimpleStringProperty(valor.getValue().getPago().getNumeroTransaccion()));

        // Añade las columnas
        tabla.getColumns().addAll(colNomCliente, colApCliente, colLocalidadCliente, colNumPlan, colNumCuota,
                colImporteCuota, colTipoPago, colBanco, colFecha, colImporteDeposito, colNroTansaccion);
        panelGeneral.setCenter(tabla);
    }

    public void iniciarBarraAgregarPlan() {
        Label[] etiquetas = new Label[5];
        Node[] nodos = new Node[5];
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


    public void ocultarBarraAgregarPlan() {
        barraAgregarPlan.setVisible(false);
        barraAgregarPlan.setManaged(false);

    }

    public void mostrarBarraAgregarPlan() {
        barraAgregarPlan.setVisible(false);
        barraAgregarPlan.setManaged(false);

    }

    // Agrega una nueva entrada a la tabla
    public void agregarFila(Fila fila) {
        tabla.getItems().add(fila);
    }

    public Label[] getEtiquetasBarraUtilidades() {
        return etiquerasBarraInserccion;
    }

    public void setEtiquetasBarraUtilidades(Label[] etiquerasBarraInserccion) {
        this.etiquerasBarraInserccion = etiquerasBarraInserccion;
    }

    public TextField getPlan() {
        return plan;
    }

    public void setPlan(TextField plan) {
        this.plan = plan;
    }

    public TextField getImporte() {
        return importe;
    }

    public void setImporte(TextField importe) {
        this.importe = importe;
    }

    public DatePicker getFecha() {
        return fecha;
    }

    public void setFecha(DatePicker fecha) {
        this.fecha = fecha;
    }

    public ComboBox<String> getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(ComboBox<String> tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public ComboBox<String> getBanco() {
        return banco;
    }

    public void setBanco(ComboBox<String> banco) {
        this.banco = banco;
    }

    public TextField getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(TextField numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

    public Button getConfirmar() {
        return confirmar;
    }

    public void setConfirmar(Button confirmar) {
        this.confirmar = confirmar;
    }

    public TextField getTitulo() {
        return titulo;
    }

    public void setTitulo(TextField titulo) {
        this.titulo = titulo;
    }

    public Button getGuardar() {
        return guardar;
    }

    public void setGuardar(Button guardar) {
        this.guardar = guardar;
    }

    public Button getAtras() {
        return atras;
    }

    public void setAtras(Button atras) {
        this.atras = atras;
    }

    public GridPane getInserccion() {
        return inserccion;
    }

    public void setInserccion(GridPane inserccion) {
        this.inserccion = inserccion;
    }

    public VBox getBarraSuperior() {
        return barraSuperior;
    }

    public void setBarraSuperior(VBox barraSuperior) {
        this.barraSuperior = barraSuperior;
    }

    public HBox getBarraUtilidades() {
        return barraUtilidades;
    }

    public void setBarraUtilidades(HBox barraUtilidades) {
        this.barraUtilidades = barraUtilidades;
    }

    public BorderPane getPanelGeneral() {
        return panelGeneral;
    }

    public void setPanelGeneral(BorderPane panelGeneral) {
        this.panelGeneral = panelGeneral;
    }

    public TableView<Fila> getTabla() {
        return tabla;
    }

    public void setTabla(TableView<Fila> tabla) {
        this.tabla = tabla;
    }

    public ControladorInformes getControlador() {
        return controlador;
    }

    public void setControlador(ControladorInformes controlador) {
        this.controlador = controlador;
    }

    public TextField getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(TextField numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Button getBuscar() {
        return buscar;
    }

    public void setBuscar(Button buscar) {
        this.buscar = buscar;
    }

    public GridPane getBarraAgregarPlan() {
        return barraAgregarPlan;
    }

    public void setBarraAgregarPlan(GridPane barraAgregarPlan) {
        this.barraAgregarPlan = barraAgregarPlan;
    }

    public Button getConfAgregarPlan() {
        return confAgregarPlan;
    }

    public void setConfAgregarPlan(Button confAgregarPlan) {
        this.confAgregarPlan = confAgregarPlan;
    }

    public TextField getNumeroDePlan() {
        return numeroDePlan;
    }

    public void setNumeroDePlan(TextField numeroDePlan) {
        this.numeroDePlan = numeroDePlan;
    }

    public TextField getImporteDelPlan() {
        return importeDelPlan;
    }

    public void setImporteDelPlan(TextField importeDelPlan) {
        this.importeDelPlan = importeDelPlan;
    }

    public TextField getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(TextField nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public TextField getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(TextField apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

}
