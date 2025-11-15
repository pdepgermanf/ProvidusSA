package pro.Informes.InformesImpagos;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import pro.Entidad.Informe;
import pro.Informes.Fila;
import pro.Informes.InterfazInforme;
import pro.MenuPrincipal.Main;

public class InterfazInformesImpagos extends InterfazInforme {
    private ControladorInformesImpagos controlador;
    private TextField motivo;
    
    public InterfazInformesImpagos(Main control) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        controlador = new ControladorInformesImpagos(this, control);
        control.getVentana().setTitle("Informe Impagos");
    }

    //Constructor en el caso de modificacion de informe
    public InterfazInformesImpagos(Main control,Informe informe) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        controlador = new ControladorInformesImpagos(this, control,informe);
        control.getVentana().setTitle("Informe Impagos");
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
        TableColumn<Fila, String> colMotivo = new TableColumn<>("Motivo");
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));

        // Añade las columnas
        tabla.getColumns().addAll(colNomCliente, colApCliente, colLocalidadCliente, colNumPlan, colNumCuota,colMotivo);
        panelGeneral.setCenter(tabla);
    }

    @Override
    protected void iniciarPanelSuperior() {
        // Lista para almacenar elementos
        Node[] nodos = new Node[5];
        inserccion = new GridPane();
        inserccion.setHgap(5);
        inserccion.setVgap(1);

        // Entradas para panel inserccion
        nodos[0] = plan = new TextField("");
        nodos[1] = buscar = new Button("Buscar");
        nodos[2]= numeroCuota = new TextField();
        nodos[3]= motivo = new TextField();
        nodos[4] = confirmar = new Button("Confirmar");

        etiquerasBarraInserccion = new Label[5];
        etiquerasBarraInserccion[0] = new Label("Plan");
        etiquerasBarraInserccion[1] = new Label();
        etiquerasBarraInserccion[2] = new Label("Numero cuota");
        etiquerasBarraInserccion[3] = new Label("Motivo");
        etiquerasBarraInserccion[4] = new Label();

        for (int i = 0; i < etiquerasBarraInserccion.length; i++) {
            inserccion.add(etiquerasBarraInserccion[i], i, 0);
            inserccion.add(nodos[i], i, 1);
        }
        barraSuperior.getChildren().add(inserccion);
    }



    public TextField getMotivo() {
        return motivo;
    }

    public void setMotivo(TextField motivo) {
        this.motivo = motivo;
    }

    
}
