package pro.Informes.InformesActualizacion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.control.TableColumn;
import pro.Entidad.Informe;
import pro.Informes.ControladorInformes;
import pro.Informes.Fila;
import pro.MenuPrincipal.Main;
import pro.Utilidades.Busqueda;
import pro.Utilidades.ConexionSQL;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ControladorInformesActualizacion extends ControladorInformes {

    private InterfazInformeActualizacion ventanaInformes;
    private Informe informe;

    public ControladorInformesActualizacion(InterfazInformeActualizacion ventanaInformes, Main control) {
        super(control);
        this.ventanaInformes = ventanaInformes;
        this.informe = new Informe(ventanaInformes.getTitulo().getText(),"ACTUALIZACIONES");
        agregarManejadores();
    }

    public ControladorInformesActualizacion(InterfazInformeActualizacion ventanaInformes, Main control, Informe informe) {
        super(control);
        this.ventanaInformes = ventanaInformes;
        this.informe = informe;
        agregarManejadores();
        recuperarInforme();
    }

    protected void agregarManejadores() {
        ventanaInformes.getAtras().setOnAction(evento -> volverAtras());
        ventanaInformes.getConfirmar().setOnAction(evento -> confirmar());
        ventanaInformes.getBuscar().setOnAction(evento -> buscarPlan());
        ventanaInformes.getGuardar().setOnAction(evento -> guardar());
        ventanaInformes.getExportarXslx().setOnAction(evento->exportarXlsx());
        ventanaInformes.getTitulo().textProperty().addListener((l, n,o)-> modificarTitulo(n));
    }

    public void modificarTitulo(String nuevo){
        informe.setTitulo(nuevo);
    }

    // Busca el plan introducido
    protected void buscarPlan() {
        int numeroPlan = Integer.parseInt(ventanaInformes.getPlan().getText());
        // Verifica si existe dicho plan
        if (Busqueda.existePlan(numeroPlan)) {
            ventanaInformes.mostrarMensaje("El plan se encuentra cargado", "Confirmacion");
        }
    }

    // Busca la informacion en la BD y actualiza la tabla y la entidad fila
    // almacenada.
    protected void confirmar() {
        ResultSet resultado;
        int numeroPlan = Integer.parseInt(ventanaInformes.getPlan().getText());
        String consultaSql = "select nombre, apellido,localidad from cliente,plan where cliente.id_cliente=plan.id_cliente and plan.id_plan="
                + numeroPlan;
        try {
            resultado = ConexionSQL.consultar(consultaSql);
            if (resultado.next()) {
                // Actializa la tabla
                String nombre = resultado.getString(1);
                String apellido = resultado.getString(2);
                String localidad = resultado.getString(3);
                int numeroCuota = Integer.parseInt(ventanaInformes.getNumeroCuota().getText());
                double nuevoImporte = Double.parseDouble(ventanaInformes.getNuevoImporte().getText());
                String tipoPago = ventanaInformes.getTipoDePago().getValue();
                String banco = ventanaInformes.getBanco().getValue();
                String fecha = ventanaInformes.getFecha().getValue().toString();
                double importeDeposito = Double.parseDouble(ventanaInformes.getImporte().getText());
                String numeroTransaccion = ventanaInformes.getNumeroTransaccion().getText();
                Fila fila = new Fila(nombre, apellido, localidad, numeroPlan, numeroCuota, nuevoImporte, tipoPago,
                        banco, fecha, importeDeposito, numeroTransaccion);
                // Actualiza la tabla y la entidad fila
                ventanaInformes.agregarFila(fila);
                informe.agregarEntrada(fila);
            } else {
                ventanaInformes.mostrarMensaje("No se encontro el plan indicado", "Error al insertar");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el plan");

        }
    }

    // Realiza el guardado de las filas en la base de datos
    protected void guardar() {
        ArrayList<Fila> lista = informe.getEntradas();
        String sql = String.format("INSERT IGNORE INTO INFORME(tipo,titulo,fecha) values (\"%s\",\"%s\",\"%s\");",
                informe.getTipoDeInforme(),
                informe.getTitulo(), informe.getFecha());
        ConexionSQL.operacionIUD(sql);
        for (Fila fila : lista) {
            sql = String.format(Locale.US,
                    "INSERT IGNORE INTO PAGO(numero_transaccion,tipo_pago,nom_banco,importe_pago,fecha_transaccion) values(\"%s\",\"%s\",\"%s\",%f,\"%s\") ",
                    fila.getPago().getNumeroTransaccion(), fila.getPago().getTipoPago(), fila.getPago().getBanco(),
                    fila.getPago().getImporte(), fila.getPago().getFecha());
            ConexionSQL.operacionIUD(sql);
            sql = String.format(Locale.US,
                    "INSERT IGNORE INTO CUOTA(id_plan,num_cuota,importe_cuota,estado,numero_transaccion) values(%d,%d,%f,\"%s\",\"%s\")",
                    fila.getPlan().getNumeroPlan(), fila.getPlan().getNumeroCuota(), fila.getPlan().getImporte(),
                    "pagado", fila.getPago().getNumeroTransaccion());
            ConexionSQL.operacionIUD(sql);
            sql = String.format(Locale.US,
                    "INSERT IGNORE INTO DETALLE_INFORME(id_informe,id_plan,num_cuota,observacion) values(%d,%d,%d,\"%s\")",
                    informe.getIdInforme(), fila.getPlan().getNumeroPlan(), fila.getPlan().getNumeroCuota(),
                    "Pago de cuota");
            ConexionSQL.operacionIUD(sql);
            sql = String.format(Locale.US,
                    "UPDATE PLAN SET importe_plan=%f WHERE id_plan=%d", fila.getPlan().getImporte(),
                    fila.getPlan().getNumeroPlan());
            ConexionSQL.operacionIUD(sql);
        }
    }

    @Override
    protected void exportarXlsx() {
        Workbook archivo = new XSSFWorkbook();
        Sheet hoja = archivo.createSheet("Informe");
        int tamaño;
        //Crea el estilo para el titulo
        CellStyle estiloTitulo = archivo.createCellStyle();
        estiloTitulo.setAlignment(HorizontalAlignment.CENTER);

        //Recopila los titulos de la tabla
        ArrayList<String> titulos = new ArrayList<>();
        for (TableColumn<Fila,?> columna : ventanaInformes.getTabla().getColumns()) {
            titulos.add(columna.getText());
        }
        tamaño=titulos.size();

        //Añade el titulo en la primera fila
        Row titulo = hoja.createRow(0);
        Cell celdaTitulo =titulo.createCell(0);
        celdaTitulo.setCellValue( informe.getTitulo());
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, tamaño-1));
        celdaTitulo.setCellStyle(estiloTitulo);

        
        //Recopila los titulos y los introduce en el archivo xslx
        Row encabezado= hoja.createRow(1);
        for (int i = 0; i < tamaño ; i++) {
            encabezado.createCell(i).setCellValue(titulos.get(i));
        }

        ArrayList<Fila> filasRecuperadas=informe.getEntradas();
        //Realiza la carga de las demas filas
        for (int i = 0; i < filasRecuperadas.size(); i++) {
            Row fila = hoja.createRow(i+2);
            String[] celdas= filasRecuperadas.get(i).recuperarCeldas();
            for (int j = 0; j <tamaño ; j++) {
                fila.createCell(j).setCellValue(celdas[j]);
            }
        }

        //Ajusta el tamaño de las columna segun el contenido
        for (int i = 0; i < tamaño; i++) {
            hoja.autoSizeColumn(i);
        }

        try {
            FileOutputStream archivoSalida = new FileOutputStream(informe.getTitulo()+".xlsx");
            archivo.write(archivoSalida);
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperarInforme() {
        int idInforme = informe.getIdInforme();
        String sql = String.format(
                "SELECT nombre,apellido,localidad,di.id_plan,cu.num_cuota,importe_cuota,tipo_pago,nom_banco,fecha_transaccion,importe_pago,pa.numero_transaccion FROM DETALLE_INFORME di JOIN CUOTA cu ON di.id_plan=cu.id_plan AND di.num_cuota=cu.num_cuota JOIN PAGO pa ON cu.numero_transaccion=pa.numero_transaccion JOIN PLAN pl ON di.id_plan=pl.id_plan JOIN CLIENTE cli ON cli.id_cliente=pl.id_cliente WHERE id_informe=%d;",
                idInforme);
        ResultSet resultado = ConexionSQL.consultar(sql);
        try {
            while (resultado.next()) {
                String nombre = resultado.getString(1);
                String apellido = resultado.getString(2);
                String localidad = resultado.getString(3);
                int idPlan = resultado.getInt(4);
                int numCuota = resultado.getInt(5);
                double importe_cuota = resultado.getDouble(6);
                String tipoPago = resultado.getString(7);
                String nomBanco = resultado.getString(8);
                String fecha = resultado.getString(9);
                Double importePago = resultado.getDouble(10);
                String nroTransaccion = resultado.getString(11);
                Fila fila = new Fila(nombre, apellido, localidad, idPlan, numCuota, importe_cuota, tipoPago, nomBanco,
                        fecha, importe_cuota, nroTransaccion);
                ventanaInformes.agregarFila(fila);
                informe.agregarEntrada(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}