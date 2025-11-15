package pro.AministrarClientes.AgregarCliente;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pro.Interfaz;
import pro.MenuPrincipal.Main;
public class InterfazClientesAgregar extends Interfaz{
    TextField nombre;
    TextField apellido;
    TextField localidad;
    TextField telefono;
    Button confirmar;
    GridPane barraInserccion;

    public InterfazClientesAgregar(Main control){
        super(control);
        iniciarPanelCentral();
        control.getVentana().setTitle("Agregar Cliente");
        new ControladorAgregarCliente(this, control);
    }



    @Override
    protected void iniciarPanelCentral() {
        Label[] etiquetas= new Label[5];
        Node[] nodos = new Node[5];
        nodos[0]=nombre= new TextField();
        nodos[1]=apellido= new TextField();
        nodos[2]=localidad= new TextField();
        nodos[3]=telefono= new TextField();
        nodos[4]=confirmar= new Button("Agregar");

        etiquetas[0]= new Label("Nombre");
        etiquetas[1]= new Label("Apellido");
        etiquetas[2]= new Label("Localidad"); 
        etiquetas[3]= new Label("Telefono");
        etiquetas[4]= new Label();

        barraInserccion= new GridPane();

        for (int i = 0; i < etiquetas.length ; i++) {
            barraInserccion.add(etiquetas[i], i, 0);
            barraInserccion.add(nodos[i], i, 1);            
        }
        panelGeneral.setCenter(barraInserccion);
        barraInserccion.setAlignment(Pos.CENTER);
        
    }

    @Override
    protected void iniciarPanelSuperior() {
        
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

    public Button getConfirmar() {
        return confirmar;
    }

    public GridPane getBarraInserccion() {
        return barraInserccion;
    }



    
    
}
