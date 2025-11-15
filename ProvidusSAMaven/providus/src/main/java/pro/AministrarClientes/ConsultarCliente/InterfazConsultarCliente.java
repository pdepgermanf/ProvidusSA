package pro.AministrarClientes.ConsultarCliente;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import pro.Interfaz;
import pro.Entidad.Cliente;
import pro.MenuPrincipal.Main;

public class InterfazConsultarCliente extends Interfaz {
    private Button confirmar;
    private TableView<Cliente> tablaClientes;
    private TextField buscador;
    private Button botonBuscar;
    private RadioButton opcionNombre;
    private RadioButton opcionApellido;
    private RadioButton opcionID;
    private RadioButton opcionLocalidad;
    private HBox barraDeBusqueda;
    

    
    public InterfazConsultarCliente(Main control){
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        control.getVentana().setTitle("Ver clientes");
        new ControladorConsultarCliente(this, control);
    }


    public void añadirFila(Cliente fila){
        tablaClientes.getItems().add(fila);
    }
    
    @Override
    protected void iniciarPanelCentral() {
        tablaClientes= new TableView<>();
        TableColumn<Cliente,Integer> colIdCliente = new TableColumn<>("ID");
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Cliente,String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cliente,String> colApellido = new TableColumn<>("Apellido");
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TableColumn<Cliente,String> colLocalidad = new TableColumn<>("Localidad");
        colLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        TableColumn<Cliente,String> colTelefono = new TableColumn<>("Telefono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaClientes.getColumns().addAll(colIdCliente,colNombre,colApellido,colLocalidad,colTelefono);
        panelGeneral.setCenter(tablaClientes);
    }

    @Override
    protected void iniciarPanelSuperior() {
        botonBuscar= new Button("Buscar");
        buscador= new TextField();
        ToggleGroup grupoDeOpciones = new ToggleGroup();
        opcionNombre= new RadioButton("Nombre");
        opcionApellido= new RadioButton("Apellido");
        opcionID= new RadioButton("ID");
        opcionLocalidad= new RadioButton("Localidad");

        opcionNombre.setToggleGroup(grupoDeOpciones);
        opcionID.setToggleGroup(grupoDeOpciones);
        opcionApellido.setToggleGroup(grupoDeOpciones);
        opcionLocalidad.setToggleGroup(grupoDeOpciones);

        barraDeBusqueda= new HBox(5);
        barraDeBusqueda.getChildren().addAll(opcionNombre,opcionApellido,opcionID,opcionLocalidad,buscador,botonBuscar);
        barraSuperior.getChildren().add(barraDeBusqueda);
    }

    public Button getConfirmar() {
        return confirmar;
    }

    public TableView<Cliente> getTablaClientes() {
        return tablaClientes;
    }

    public TextField getBuscador() {
        return buscador;
    }

    public Button getBotonBuscar() {
        return botonBuscar;
    }

    public RadioButton getOpcionNombre() {
        return opcionNombre;
    }

    public RadioButton getOpcionApellido() {
        return opcionApellido;
    }

    public RadioButton getOpcionID() {
        return opcionID;
    }

    public RadioButton getOpcionLocalidad() {
        return opcionLocalidad;
    }

    public HBox getBarraDeBusqueda() {
        return barraDeBusqueda;
    }
    
}
