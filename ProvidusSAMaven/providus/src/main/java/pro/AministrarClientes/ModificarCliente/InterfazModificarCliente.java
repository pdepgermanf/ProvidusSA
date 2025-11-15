package pro.AministrarClientes.ModificarCliente; 
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import pro.Interfaz;
import pro.MenuPrincipal.Main;

public class InterfazModificarCliente extends Interfaz {
    private Button confirmar;
    private ControladorModificarCliente controlador;
    private TextField buscadorID;
    private TextField buscadorNombre;
    private TextField buscadorApellido;
    private Button botonBuscar;
    private RadioButton opcionNombreApellido;
    private RadioButton opcionID;
    private HBox barraDeBusqueda;
    private Button guardarCambios;
    private TextField nombre;
    private TextField apellido;
    private TextField localidad;
    private TextField telefono;
    private Label id;
    
    public InterfazModificarCliente(Main control){
        super(control);
        iniciarPanelSuperior();
        iniciarPanelCentral();
        control.getVentana().setTitle("Modificar Cliente");
        this.controlador=new ControladorModificarCliente(this,control);
    }

    public void limpiarValores(){
        id.setText("");
        nombre.setText("");
        apellido.setText("");
        localidad.setText("");
        telefono.setText("");

        buscadorApellido.setText("");
        buscadorID.setText("");
        buscadorNombre.setText("");
    }


    @Override
    protected void iniciarPanelCentral() {
        GridPane contenedorNodos= new GridPane();
        contenedorNodos.setVgap(20);
        contenedorNodos.setAlignment(Pos.TOP_CENTER);

        Label[] etiquetas = new Label[5];
        Node[] nodos = new Node[5];
        etiquetas[0]= new Label("ID:");
        etiquetas[1]= new Label("NOMBRE:");
        etiquetas[2]= new Label("APELLIDO");
        etiquetas[3]= new Label("LOCALIDAD:");
        etiquetas[4]= new Label("TELEFONO");

        nodos[0]= id=new Label("");
        nodos[1]= nombre=new TextField();
        nodos[2]= apellido=new TextField();
        nodos[3]= localidad=new TextField();
        nodos[4]= telefono=new TextField();
        

        for (int i = 0; i < nodos.length; i++) {
            contenedorNodos.add(etiquetas[i],0,i);
            contenedorNodos.add(nodos[i], 1, i);
        }

        guardarCambios= new Button("Guardar cambios");
        contenedorNodos.add(guardarCambios, 1, nodos.length+1);
        panelGeneral.setCenter(contenedorNodos);
    }

    @Override
    protected void iniciarPanelSuperior() {
        botonBuscar= new Button("Buscar");
        buscadorID= new TextField();
        buscadorNombre= new TextField();
        buscadorApellido= new TextField();

        ToggleGroup grupoDeOpciones = new ToggleGroup();
        opcionNombreApellido= new RadioButton("Nombre y Apellido");
        opcionID= new RadioButton("ID");

        opcionNombreApellido.setToggleGroup(grupoDeOpciones);
        opcionID.setToggleGroup(grupoDeOpciones);

        //Muestra por defecto seleciconado la entrada para ID
        opcionID.setSelected(true);

        barraDeBusqueda= new HBox(5);
        barraDeBusqueda.setAlignment(Pos.TOP_CENTER);
        barraDeBusqueda.getChildren().addAll(opcionNombreApellido,opcionID,buscadorID,buscadorNombre,buscadorApellido,botonBuscar);
        barraSuperior.getChildren().add(barraDeBusqueda);

        //Los oculta por defecto
        buscadorNombre.setVisible(false);
        buscadorApellido.setVisible(false);
        buscadorNombre.setManaged(false);
        buscadorApellido.setManaged(false);
    }


    public Button getConfirmar() {
        return confirmar;
    }



    public ControladorModificarCliente getControlador() {
        return controlador;
    }



    public TextField getBuscadorID() {
        return buscadorID;
    }



    public TextField getBuscadorNombre() {
        return buscadorNombre;
    }



    public TextField getBuscadorApellido() {
        return buscadorApellido;
    }



    public Button getBotonBuscar() {
        return botonBuscar;
    }



    public RadioButton getOpcionNombreApellido() {
        return opcionNombreApellido;
    }



    public RadioButton getOpcionID() {
        return opcionID;
    }



    public HBox getBarraDeBusqueda() {
        return barraDeBusqueda;
    }



    public Button getGuardarCambios() {
        return guardarCambios;
    }



    public TextField getNombre() {
        return nombre;
    }



    public TextField getApellido() {
        return apellido;
    }



    public TextField getLocalidad() {
        return localidad;
    }



    public TextField getTelefono() {
        return telefono;
    }



    public Label getId() {
        return id;
    }

    


}
