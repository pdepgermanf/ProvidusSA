package pro;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pro.MenuPrincipal.Main;

public abstract class Interfaz {
    protected BorderPane panelGeneral;
    protected VBox barraSuperior;
    protected HBox barraUtilidades;
    protected Button atras;

    protected Interfaz(Main control){
        panelGeneral= new BorderPane(); 
        barraSuperior= new VBox();
        atras = new Button("Atras");
        barraUtilidades= new HBox(atras);
        barraSuperior.getChildren().add(barraUtilidades);
        panelGeneral.setTop(barraSuperior);
        Scene escena= new Scene(panelGeneral,1000,1000);
        control.getVentana().setScene(escena);
    }

    protected abstract void iniciarPanelCentral();

    protected abstract void iniciarPanelSuperior();

    //Getters
    public BorderPane getPanelGeneral() {
        return panelGeneral;
    }


    public VBox getBarraSuperior() {
        return barraSuperior;
    }


    public HBox getBarraUtilidades() {
        return barraUtilidades;
    }


    public Button getAtras() {
        return atras;
    }
    
}
