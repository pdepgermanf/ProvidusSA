package pro.Informes.ModificarElminarInformes;
import java.sql.ResultSet;
import java.sql.SQLException;
import pro.Entidad.Informe;
import pro.Informes.InformeBajas.InterfazInformesBajas;
import pro.Informes.InformesActualizacion.InterfazInformeActualizacion;
import pro.Informes.InformesImpagos.InterfazInformesImpagos;
import pro.Informes.InformesPagos.InterfazInformePagos;
import pro.MenuPrincipal.Main;
import pro.Utilidades.ConexionSQL;

public class ControladorModificarInforme {
    protected InterfazModificarInforme interfaz;
    protected Main control;

    public ControladorModificarInforme(InterfazModificarInforme interfaz, Main control) {
        this.interfaz = interfaz;
        this.control = control;
        interfaz.getAtras().setOnAction(e -> volverAtras());
        recuperarInformes();
    }

    public void recuperarInformes() {
        String sql = "SELECT id_informe,tipo,titulo,fecha FROM INFORME";
        ResultSet resultado = ConexionSQL.consultar(sql);
        try {
            while (resultado.next()) {
                Informe informe = new Informe(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
                        resultado.getString(4));
                interfaz.agregarEntrada(informe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarInforme(Informe informe) {
        String tipo=informe.getTipoDeInforme();
        if(tipo.equals("PAGOS")) {
            new InterfazInformePagos(control,informe);
        } else if (tipo.equals("ACTUALIZACIONES")){
            new InterfazInformeActualizacion(control,informe);
        } else if (tipo.equals("BAJAS")) {
            new InterfazInformesBajas(control,informe);
        } else if (tipo.equals("IMPAGOS")) {
            new InterfazInformesImpagos(control,informe);
        }
    }
    public void eliminarInforme(int id, int indice) {
        String sql = String.format("DELETE FROM DETALLE_INFORME WHERE id_informe=%d", id);
        ConexionSQL.operacionIUD(sql);
        sql = String.format("DELETE FROM INFORME WHERE id_informe=%d", id);
        ConexionSQL.operacionIUD(sql);
        interfaz.getTabla().getItems().remove(indice);
    }

    protected void volverAtras() {
        control.mostrarMenuPrincipal();
    }

    

}
