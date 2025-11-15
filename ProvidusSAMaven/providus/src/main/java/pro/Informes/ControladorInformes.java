package pro.Informes;
import pro.MenuPrincipal.Main;
public abstract class ControladorInformes {
    protected Main control;

    public ControladorInformes(Main control) {
        this.control = control;
    }

    //Agrega los manejadores de eventos
    protected abstract void agregarManejadores();

    // Busca el plan introducido
    protected abstract void buscarPlan();

    // Busca la informacion en la BD y actualiza la tabla y la entidad fila
    // almacenada.
    protected abstract void confirmar();

    // Realiza el guardado de las filas en la base de datos
    protected abstract void guardar();

    protected void volverAtras() {
        control.mostrarMenuPrincipal();
    }

    //Exporta el archivo en formato xlsx
    protected abstract void exportarXlsx();

    //Recupera los datos de un informe exitente
    protected abstract void recuperarInforme();
}
