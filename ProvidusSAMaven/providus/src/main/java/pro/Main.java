package pro;
import javafx.application.Application;
import javafx.stage.Stage;
import pro.Informes.InformesPagos.InterfazInformePagos;


//Esta clase controla la navegacion de la aplicacion
public class Main extends Application {
    private Stage ventana;
    
    public Stage getVentana() {
        return ventana;
    }
    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }
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

    public void mostrarInterfazInformes(){
        InterfazInformePagos informe = new InterfazInformePagos(this);
    }

    

    
}
