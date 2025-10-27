package pro;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControladorMenu {


    InterfazMenuPrincipal ventana;
    Main control;

    public ControladorMenu(InterfazMenuPrincipal ventana, Main control) {

        this.ventana = ventana;
        this.control = control;
        Button botonesPrimarios[] = ventana.getBotonesPrincipales();
        // Se agregan los manejadores de eventos
        for (int i = 0; i < botonesPrimarios.length; i++) {
            botonesPrimarios[i].setOnAction(e -> mostrarOpciones(e));
        }
    }

    // manejador de eventos de los botones

    public void mostrarOpciones(ActionEvent evento) {
        Button botonPresionado = (Button) evento.getSource();
        ArrayList<Button> botones = new ArrayList<>();
        int col = 0;
        if (botonPresionado.getText() == "Cliente") {
            botones.add(new Button("Agregar cliente"));
            botones.add(new Button("Modificar cliente"));
            botones.add(new Button("Borrar cliente"));
            botones.add(new Button("Ver clientes"));
            botones.add(new Button("Buscar cliente"));
            for (Button boton : botones) {
                boton.setOnAction(e->irCliente(e));
            }
            col = 0;

        } else if (botonPresionado.getText() == "Cobrador") {
            botones.add(new Button("Modificar mi informacion"));
            botones.add(new Button("Consultar mi informacion"));
            for (Button boton : botones) {
                boton.setOnAction(e->irCobrador(e));
            }
            col = 3;
        } else if (botonPresionado.getText() == "Informes") {
            botones.add(new Button("Crear informe bajas"));
            botones.add(new Button("Crear informe pagos"));
            botones.add(new Button("Crear informe impagos"));
            botones.add(new Button("Crear infrome con actualizacion"));
            botones.add(new Button("Modificar informe"));
            botones.add(new Button("Eliminar informe"));
            for (Button boton : botones) {
                boton.setOnAction(e->irInformes(e));
            }
            col = 1;

        } else if (botonPresionado.getText() == "Mensajes") {
            botones.add(new Button("Crear nuevo mensaje"));
            botones.add(new Button("Modificar mensaje"));
            botones.add(new Button("Eliminar mensaje"));
            botones.add(new Button("Enviar mensaje"));
            for (Button boton : botones) {
                boton.setOnAction(e->irMensajes(e));
            }
            col = 2;
        }

        // modifica los estilos
        for (Button boton : botones) {
            boton.getStyleClass().add("textoComun");
        }

        // Actualiza el el boton presionado
        ventana.getPanelCentral().getChildren().remove(col);

        // Crea un panel interior
        VBox panelVertical = new VBox(5);
        panelVertical.getChildren().addAll(botones);
        // modifica las dimensiones
        for (Button boton : botones) {
            boton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            panelVertical.setVgrow(boton, Priority.ALWAYS);

        }

        ventana.getPanelCentral().add(panelVertical, col, 0);

    }

    // Lleva a alguna de las interfaces de creacion de informes
    public void irInformes(ActionEvent evento) {
        Button botonPresionado = (Button) evento.getSource();
        if (botonPresionado.getText() == "Crear informe pagos") {
            control.mostrarInterfazInformes();
        } else if (botonPresionado.getText() == "Crear informe impagos") {
            //Por implementar
        } else if (botonPresionado.getText() == "Crear informe bajas") {
            //Por implementar
        } else if (botonPresionado.getText() == "Crear infrome con actualizacion") {
            //Por implementar
        } else if (botonPresionado.getText() == "Modificar informe") {
            //Por implementar
        } else if (botonPresionado.getText() == "Eliminar informe") {
            //Por implementar
        }
    }

    public void irMensajes(ActionEvent evento) {
        Button botonPresionado = (Button) evento.getSource();
        System.out.print(botonPresionado.getText());
        if (botonPresionado.getText() == "Crear nuevo mensaje") {
            //Por implementar
        } else if (botonPresionado.getText() == "Modificar mensaje") {
            //Por implementar
        } else if (botonPresionado.getText() == "Eliminar mensaje") {
            //Por implementar
        } else if (botonPresionado.getText() == "Enviar mensaje") {
            //Por implementar
        } 
    }

    public void irCobrador(ActionEvent evento) {
        Button botonPresionado = (Button) evento.getSource();
        System.out.print(botonPresionado.getText());
        if (botonPresionado.getText() == "Modificar mi informacion") {
            //Por implementar
        } else if (botonPresionado.getText() == "Consultar mi informacion") {
            //Por implementar
        }

    }

    public void irCliente(ActionEvent evento) {
        Button botonPresionado = (Button) evento.getSource();
        System.out.print(botonPresionado.getText());
        if (botonPresionado.getText() == "Agregar cliente") {
            //Por implementar
        } else if (botonPresionado.getText() == "Modificar cliente") {
            //Por implementar
        } else if (botonPresionado.getText() == "Borrar cliente") {
            //Por implementar
        } else if (botonPresionado.getText() == "Ver clientes") {
            //Por implementar
        } else if (botonPresionado.getText() == "Modificar informe") {
            //Por implementar
        } else if (botonPresionado.getText() == "Buscar cliente") {
            //Por implementar
        }

    }
}
