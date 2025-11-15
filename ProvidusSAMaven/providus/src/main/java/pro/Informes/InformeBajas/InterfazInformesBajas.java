package pro.Informes.InformeBajas;
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

public class InterfazInformesBajas extends InterfazInforme {
    private ControladorInformesBajas controlador;
    private TextField motivo;

    public InterfazInformesBajas(Main control) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        controlador = new ControladorInformesBajas(this, control);
        control.getVentana().setTitle("Informe bajas");
    }

    //Constructor en el caso de modificacion del iforme
    public InterfazInformesBajas(Main control,Informe informe) {
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        controlador = new ControladorInformesBajas(this, control,informe);
        control.getVentana().setTitle("Informe bajas");
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
        TableColumn<Fila, String> colMotivo = new TableColumn<>("Motivo");
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));

        // Añade las columnas
        tabla.getColumns().addAll(colNomCliente, colApCliente, colLocalidadCliente, colNumPlan,colMotivo);
        panelGeneral.setCenter(tabla);
    }

    @Override
    protected void iniciarPanelSuperior() {
        // Lista para almacenar elementos
        Node[] nodos = new Node[4];
        inserccion = new GridPane();
        inserccion.setHgap(5);
        inserccion.setVgap(1);

        // Entradas para panel inserccion
        nodos[0] = plan = new TextField("");
        nodos[1] = buscar = new Button("Buscar");
        nodos[2]= motivo = new TextField();
        nodos[3] = confirmar = new Button("Confirmar");

        etiquerasBarraInserccion = new Label[4];
        etiquerasBarraInserccion[0] = new Label("Plan");
        etiquerasBarraInserccion[1] = new Label();
        etiquerasBarraInserccion[2] = new Label("Motivo");
        etiquerasBarraInserccion[3] = new Label();

        for (int i = 0; i < etiquerasBarraInserccion.length; i++) {
            inserccion.add(etiquerasBarraInserccion[i], i, 0);
            inserccion.add(nodos[i], i, 1);
        }
        barraSuperior.getChildren().add(inserccion);
    }


    public TextField getMotivo() {
        return motivo;
    }

    
    
}
