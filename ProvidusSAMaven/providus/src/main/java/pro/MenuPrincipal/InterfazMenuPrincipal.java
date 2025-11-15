package pro.MenuPrincipal;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class InterfazMenuPrincipal {
    private GridPane panelCentral;
    private BorderPane panelGeneral;
    private Button botonesPrincipales[]= new Button[4];

    public InterfazMenuPrincipal(Main control) {
        iniciarPanelCentral();
        Scene escena = new Scene(panelGeneral, 1000, 1000);
        escena.getStylesheets().add("file:providus/src/main/resources/estilos.css");
        control.getVentana().setTitle("MENU DE OPCIONES");
        control.getVentana().setScene(escena);
        //Crea el controlador de la interfaz
         new ControladorMenu(this,control);     
    }

    private void iniciarPanelCentral(){
        // Cuatro botones principales
        botonesPrincipales[0]=new Button("Cliente");
        botonesPrincipales[1] = new Button("Informes");
        botonesPrincipales[2]= new Button("Mensajes");
        botonesPrincipales[3]= new Button("Cobrador");

        panelCentral = new GridPane();
        panelCentral.setHgap(10);
        panelCentral.setVgap(10);
        

        // Divide el panel central en cuatro y los expande horizontalmente
        for (int i = 0; i < 4; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(25);
            col.setHgrow(Priority.ALWAYS);
            panelCentral.getColumnConstraints().add(col);
        }

        // Expande la fila verticalmente
        RowConstraints fila = new RowConstraints();
        fila.setVgrow(Priority.ALWAYS);
        panelCentral.getRowConstraints().add(fila);

        // Aumenta el tamaño de los botones
        for(int i=0;i<4;i++){
            botonesPrincipales[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }

        // cambia el fondo de los botones
        botonesPrincipales[0].getStyleClass().addAll("textoMenuPrincipal", "fondoCliente");
        botonesPrincipales[1].getStyleClass().addAll("textoMenuPrincipal","fondoInforme");
        botonesPrincipales[2].getStyleClass().addAll("textoMenuPrincipal","fondoMensaje");
        botonesPrincipales[3].getStyleClass().addAll("textoMenuPrincipal","fondoCobrador");

        for (int i = 0; i < botonesPrincipales.length; i++) {
            panelCentral.add(botonesPrincipales[i],i,0);
        }

        panelGeneral = new BorderPane();
        panelGeneral.setPadding(new Insets(10,10,10,10));
        panelGeneral.setCenter(panelCentral);
        
    }

    public GridPane getPanelCentral() {
        return panelCentral;
    }

    public void setPanelCentral(GridPane panelCentral) {
        this.panelCentral = panelCentral;
    }

    public Button[] getBotonesPrincipales() {
        return botonesPrincipales;
    }

    public void setBotonesPrincipales(Button[] botonesPrincipales) {
        this.botonesPrincipales = botonesPrincipales;
    }
}
