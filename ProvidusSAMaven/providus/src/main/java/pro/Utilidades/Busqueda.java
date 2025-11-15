package pro.Utilidades;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Busqueda {

    // recupera el id del cliente
    public static int busquedaCliente(String nombre, String apellido) {
        try {
            ResultSet resultado = ConexionSQL.consultar(String.format("SELECT id_cliente FROM CLIENTE WHERE nombre='%s' AND apellido='%s';", nombre, apellido));
            if (resultado.next()) {
                return resultado.getInt("id_cliente");
            }

        } catch (SQLException e) {
            System.out.println("La bsuqueda del cliente fallo");
        }
        return -1;

    }

    public static boolean existePlan(int numeroPlan) {
        ResultSet resultado = ConexionSQL.consultar("select id_plan from plan WHERE id_plan=" + numeroPlan);
        // Verifica si recupero alguna fila
        try {
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("La bsuqueda del plan fallo");
        }

        return false;

    }

}
