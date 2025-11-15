package pro.Informes;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import pro.Interfaz;
import pro.MenuPrincipal.Main;

public abstract class InterfazInforme extends Interfaz {
    protected Label[] etiquerasBarraInserccion;
    protected TextField plan;
    protected Button buscar;
    protected TextField numeroCuota;
    protected TextField importe;
    protected DatePicker fecha;
    protected ComboBox<String> tipoDePago;
    protected ComboBox<String> banco;
    protected TextField numeroTransaccion;
    protected Button confirmar;
    protected TextField titulo;
    protected Button guardar;
    protected GridPane inserccion;
    protected TableView<Fila> tabla;
    protected Button exportarXslx;

    //El constructor inicializa los elementos genericos de la ventana 
    public InterfazInforme(Main control){
        super(control);
        titulo = new TextField("Titulo");
        guardar = new Button("Guradar informe");
        exportarXslx= new Button("Exportar xslx");
        // Agrega los nodos de la barra superior
        barraUtilidades.getChildren().addAll(guardar,exportarXslx, titulo);
        barraUtilidades.setHgrow(titulo, Priority.ALWAYS);
    }

    //Muestra un mensaje emergente segun sea el caso necesario
    public void mostrarMensaje(String mensaje,String titulo) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Agrega una nueva entrada a la tabla
    public void agregarFila(Fila fila) {
        tabla.getItems().add(fila);
    }

    //Getters...
    public Label[] getEtiquerasBarraInserccion() {
        return etiquerasBarraInserccion;
    }



    //getters
    public TextField getPlan() {
        return plan;
    }

    public Button getBuscar() {
        return buscar;
    }

    public TextField getNumeroCuota() {
        return numeroCuota;
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

    public Button getConfirmar() {
        return confirmar;
    }

    public TextField getTitulo() {
        return titulo;
    }

    public Button getGuardar() {
        return guardar;
    }

    public GridPane getInserccion() {
        return inserccion;
    }

    public BorderPane getPanelGeneral() {
        return panelGeneral;
    }

    public TableView<Fila> getTabla() {
        return tabla;
    }

    public Button getExportarXslx() {
        return exportarXslx;
    }


}
