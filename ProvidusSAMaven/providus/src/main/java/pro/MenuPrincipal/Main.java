package pro.MenuPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage ventana;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ventana=primaryStage;
        mostrarMenuPrincipal();
        ventana.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

    public void mostrarMenuPrincipal(){
        InterfazMenuPrincipal ventanaMenu = new InterfazMenuPrincipal(this);
    }
    public Stage getVentana() {
        return ventana;
    }
    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

}
