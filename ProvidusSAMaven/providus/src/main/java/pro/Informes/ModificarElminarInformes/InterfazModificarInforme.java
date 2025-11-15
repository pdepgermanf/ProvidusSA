package pro.Informes.ModificarElminarInformes;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pro.Interfaz;
import pro.Entidad.Informe;
import pro.MenuPrincipal.Main;

public class InterfazModificarInforme extends Interfaz {
    protected TableView tabla;
    protected ControladorModificarInforme controlador;

    public InterfazModificarInforme(Main control) {
        super(control);
        iniciarPanelCentral();
        control.getVentana().setTitle("Ver informes");
        controlador = new ControladorModificarInforme(this, control);
    }

    @Override
    protected void iniciarPanelCentral() {
        tabla = new TableView<>();
        TableColumn<Informe, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idInforme"));
        TableColumn<Informe, String> columnaTipoInforme = new TableColumn<>("Tipo");
        columnaTipoInforme.setCellValueFactory(new PropertyValueFactory<>("tipoDeInforme"));
        TableColumn<Informe, String> columnaTitulo = new TableColumn<>("Titulo");
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        TableColumn<Informe, String> columnaFecha = new TableColumn<>("Fecha de creacion");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Informe, Void> colMod = new TableColumn<>("Modificar");
        colMod.setCellFactory(e -> new TableCell<Informe, Void>() {
            private final Button boton = new Button("Modificar");
            {
                // Asigna los eventos
                boton.setOnAction(e -> {
                    Informe informe = getTableView().getItems().get(getIndex());
                    controlador.modificarInforme(informe);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(boton);
                }
            }
        });

        TableColumn<Informe, Void> colEliminar = new TableColumn<>("Eliminar");
        //Se establecen en Void porque los botones no son atributo de informe
        colEliminar.setCellFactory(e -> new TableCell<Informe, Void>() {
            private final Button boton = new Button("Eliminar");
            {
                // Asigna los eventos
                boton.setOnAction(e -> {
                    int indice = getIndex();
                    int id = getTableView().getItems().get(getIndex()).getIdInforme();
                    controlador.eliminarInforme(id, indice);

                });
            }

            //Si la celda no esta vacia muestra el boton
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    //Muestra el boton en la celda si esta no se encuentra vacia
                    setGraphic(boton);
                }
            }
        });
        tabla.getColumns().addAll(columnaId, columnaFecha, columnaTipoInforme, columnaTitulo, colMod, colEliminar);
        panelGeneral.setCenter(tabla);
    }

    public void agregarEntrada(Informe informe) {
        tabla.getItems().add(informe);
    }

    @Override
    protected void iniciarPanelSuperior() {

    }

    public TableView getTabla() {
        return tabla;
    }

}
